#!/bin/bash

# Asserts that a single executable provided as parameter is available
function executable_exists {
	command -v "${1}" >/dev/null 2>&1 || { echo >&2 "This script requires ${1}, but it is not installed.  Aborting."; exit 1; }
}

# Generates an sftp script that uploads files created on the date passed as a parameter
function generate_sftp_script {

	echo -e "cd downloads/libraries" > generated_commands.sftp
	echo -e "put generated/libraries-${1}.zip" >> generated_commands.sftp
	echo -e "rm libraries-latest.zip" >> generated_commands.sftp	
	echo -e "put generated/libraries-latest.zip" >> generated_commands.sftp
    echo -e "" >> generated_commands.sftp
	
	echo -e "cd ../examples" >> generated_commands.sftp
	echo -e "put generated/examples-${1}.zip" >> generated_commands.sftp
	echo -e "rm examples-latest.zip" >> generated_commands.sftp
	echo -e "put generated/examples-latest.zip" >> generated_commands.sftp
	echo -e "" >> generated_commands.sftp
	
	echo -e "cd ../user-contributions" >> generated_commands.sftp
	echo -e "put generated/user-contributions-${1}.zip" >> generated_commands.sftp
	echo -e "rm user-contributions-latest.zip" >> generated_commands.sftp
	echo -e "put generated/user-contributions-latest.zip" >> generated_commands.sftp
	
	echo -e "" >> generated_commands.sftp
	echo -e "bye" >> generated_commands.sftp	
}

# Verify that requirements are satisfied
executable_exists svn
executable_exists sftp
executable_exists zip

# Store date stamp in a local variable
DATE=$(date +%Y%m%d)

# Store path to where script is executed
SCRIPT_HOME=$(pwd)

# Remove old generated files and create new output directory
OUTPUT_DIR=${SCRIPT_HOME}/generated
rm -rf ${OUTPUT_DIR}
mkdir -p ${OUTPUT_DIR}

# Update the directory in case it is out of date
cd  .. || exit # We exit in case cd fails
svn up


# Create a version file and zip it up together with the examples. Filters out .svn directory
echo "This distribution of POOSL examples and libraries was created on ${DATE}" >  version.txt
zip -r  ${OUTPUT_DIR}/examples-"${DATE}".zip  nl.esi.poosl.examples/ nl.esi.poosl.libraries/ version.txt -x -- *.svn*
cp ${OUTPUT_DIR}/examples-"${DATE}".zip ${OUTPUT_DIR}/examples-latest.zip
rm version.txt

# Create a version file and zip it up together with the libraries
echo "This distribution of POOSL libraries was created on ${DATE}" > nl.esi.poosl.libraries/version.txt 
zip -r ${OUTPUT_DIR}/libraries-"${DATE}".zip nl.esi.poosl.libraries/
cp ${OUTPUT_DIR}/libraries-"${DATE}".zip ${OUTPUT_DIR}/libraries-latest.zip
rm nl.esi.poosl.libraries/version.txt


# Create a version file and zip it up together with the user contributions. Filters out POOSL settings.
echo "This distribution of POOSL user contributions was created on ${DATE}" > nl.esi.poosl.user-contributions/version.txt
zip -r ${OUTPUT_DIR}/user-contributions-"${DATE}".zip nl.esi.poosl.user-contributions/ -x -- *.settings*
cp ${OUTPUT_DIR}/user-contributions-"${DATE}".zip ${OUTPUT_DIR}/user-contributions-latest.zip
rm nl.esi.poosl.user-contributions/version.txt


# Return to script home and upload the generated zip files to their respective folders on the server
cd ${SCRIPT_HOME} || exit # We exit in case cd fails

generate_sftp_script ${DATE}
sftp pooslupload@esi-www.tno.nl < generated_commands.sftp
rm generated_commands.sftp
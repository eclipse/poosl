<?xml version='1.0' encoding='utf-8' ?>
<xsl:stylesheet version="1.0" xmlns:po="uri:poosl" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">



  <xsl:output method="xml" indent="yes" encoding="UTF-8"/>

  <!-- This is an identity template - it copies everything 
         that doesn't match another template -->
  <xsl:template match="@*|node()">
    <xsl:copy>
      <xsl:apply-templates select="@*|node()"/>
    </xsl:copy>
  </xsl:template>

  <!-- add data method body texts 
  <xsl:template match="po:data_method">
    <xsl:choose>
      <xsl:when test="po:body_text">
        <xsl:copy/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:element name="data_method" namespace="uri:poosl">
          <xsl:attribute name="name">
            <xsl:value-of select="@name"/>
          </xsl:attribute>
          <xsl:attribute name="native">
            <xsl:value-of select="@native"/>
          </xsl:attribute>
          <xsl:for-each select="po:description">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:for-each select="po:return_type">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:for-each select="po:argument">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:for-each select="po:local_variable">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:element name="body_text" namespace="uri:poosl">
            <xsl:call-template name="data_method" />
          </xsl:element>
          <xsl:for-each select="po:body_expression">
            <xsl:copy-of select="."/>
          </xsl:for-each>
        </xsl:element>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

   add process method body texts 
  <xsl:template match="po:process_method">
    <xsl:choose>
      <xsl:when test="po:body_text">
        <xsl:copy/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:element name="process_method" namespace="uri:poosl">
          <xsl:attribute name="name">
            <xsl:value-of select="@name"/>
          </xsl:attribute>
          <xsl:for-each select="po:description">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:for-each select="po:argument">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:for-each select="po:output_parameter">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:for-each select="po:local_variable">
            <xsl:copy-of select="."/>
          </xsl:for-each>
          <xsl:element name="body_text" namespace="uri:poosl">
            <xsl:call-template name="process_method" />
          </xsl:element>
          <xsl:for-each select="po:body_statement">
            <xsl:copy-of select="."/>
          </xsl:for-each>
        </xsl:element>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

   instantiation expression texts 
  <xsl:template match="po:instantiation_expression">
    <xsl:choose>
      <xsl:when test="po:body_text">
        <xsl:copy/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:element name="instantiation_expression" namespace="uri:poosl">
          <xsl:attribute name="parameter_name">
            <xsl:value-of select="@parameter_name"/>
          </xsl:attribute>
          <xsl:element name="body_text" namespace="uri:poosl">
            <xsl:for-each select="po:body_expression/*">
              <xsl:call-template name="expression">
                <xsl:with-param name="binding_level" select="3" />
                <xsl:with-param name="horizontal_mode" select="true" />
                <xsl:with-param name="indentation_level" select="0" />
              </xsl:call-template>
            </xsl:for-each>
          </xsl:element>
          <xsl:for-each select="po:body_expression">
            <xsl:copy-of select="."/>
          </xsl:for-each>
        </xsl:element>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  add initial method call texts 
  <xsl:template match="po:initial_method_call">
    <xsl:choose>
      <xsl:when test="po:method_call_text">
        <xsl:copy/>
      </xsl:when>
      <xsl:otherwise>
        <xsl:element name="initial_method_call" namespace="uri:poosl">
          <xsl:element name="method_call_text" namespace="uri:poosl">
            <xsl:for-each select="po:process_method_call">
              <xsl:value-of select="@method_name"/>
              <xsl:text>(</xsl:text>
              <xsl:for-each select="po:argument/*">
                <xsl:call-template name="expression">
                  <xsl:with-param name="indentation_level" select="0"/>
                  <xsl:with-param name="binding" select="3"/>
                  <xsl:with-param name="horizontal" select="true"/>
                </xsl:call-template>
              </xsl:for-each>
              <xsl:text>)()</xsl:text>
            </xsl:for-each>
          </xsl:element>
          <xsl:for-each select="po:process_method_call">
            <xsl:copy-of select="."/>
          </xsl:for-each>
        </xsl:element>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>-->


  <!-- add all annotations 
      {top_level_specification { instance {connection}, channel}
      {cluster class {interface/port, channel, instance {connection}}}
      -->
  <xsl:template match="po:top_level_specification/po:instance">
    <xsl:element name="instance" namespace="uri:poosl">
      <xsl:attribute name="type">
        <xsl:value-of select="@type" />
      </xsl:attribute>
      <xsl:attribute name="class">
        <xsl:value-of select="@class" />
      </xsl:attribute>
      <xsl:attribute name="name">
        <xsl:value-of select="@name" />
      </xsl:attribute>
      <xsl:for-each select="po:instantiation_expression">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:for-each select="po:connection">
        <xsl:element name="connection" namespace="uri:poosl">
          <xsl:attribute name="port">
            <xsl:value-of select="@port"/>
          </xsl:attribute>
          <xsl:attribute name="channel">
            <xsl:value-of select="@channel"/>
          </xsl:attribute>
          <xsl:element name="annotation" namespace="uri:poosl">
            <xsl:element name="graphics" namespace="uri:poosl">
              <xsl:element name="location" namespace="uri:poosl">
                <xsl:attribute name="x">
                  <xsl:value-of select="0"/>
                </xsl:attribute>
                <xsl:attribute name="y">
                  <xsl:value-of select="0"/>
                </xsl:attribute>
              </xsl:element>
              <xsl:element name="name_location" namespace="uri:poosl">
                <xsl:attribute name="x">
                  <xsl:value-of select="0"/>
                </xsl:attribute>
                <xsl:attribute name="y">
                  <xsl:value-of select="0"/>
                </xsl:attribute>
              </xsl:element>
            </xsl:element>
          </xsl:element>
        </xsl:element>
      </xsl:for-each>
      <xsl:element name="annotation" namespace="uri:poosl">
        <xsl:element name="graphics" namespace="uri:poosl">
          <xsl:element name="box" namespace="uri:poosl">
            <xsl:attribute name="left">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="top">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="right">
              <xsl:value-of select="100"/>
            </xsl:attribute>
            <xsl:attribute name="bottom">
              <xsl:value-of select="100"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="name_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="scenario" namespace="uri:poosl">
            <xsl:attribute name="name">normal</xsl:attribute>
          </xsl:element>
        </xsl:element>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template match="po:cluster_class">
    <xsl:element name="cluster_class" namespace="uri:poosl">
      <xsl:attribute name="name">
        <xsl:value-of select="@name"/>
      </xsl:attribute>
      <xsl:for-each select="po:description">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:for-each select="po:interface">
        <xsl:element name="interface" namespace="uri:poosl">
          <xsl:for-each select="po:port">
            <xsl:call-template name="annotate_port"/>
          </xsl:for-each>
        </xsl:element>
      </xsl:for-each>
      <xsl:for-each select="po:instantiation_parameter">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:for-each select="po:channel">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:for-each select="po:instance">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:element name="annotation" namespace="uri:poosl">
        <xsl:element name="graphics" namespace="uri:poosl">
          <xsl:element name="box" namespace="uri:poosl">
            <xsl:attribute name="left">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="top">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="right">
              <xsl:value-of select="100"/>
            </xsl:attribute>
            <xsl:attribute name="bottom">
              <xsl:value-of select="100"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="name_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="scenario" namespace="uri:poosl">
            <xsl:attribute name="name">normal</xsl:attribute>
          </xsl:element>
        </xsl:element>
      </xsl:element>
    </xsl:element>

  </xsl:template>

  <xsl:template match="po:channel">
    <xsl:element name="channel" namespace="uri:poosl">
      <xsl:variable name="channelName" select="@name"/>
      <xsl:variable name="clusterClassNode" select=".."/>
      <xsl:attribute name="name">
        <xsl:value-of select="@name"/>
      </xsl:attribute>
      <xsl:if test="@output_port">
        <xsl:attribute name="output_port">
          <xsl:value-of select="@output_port"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:element name="annotation" namespace="uri:poosl">
        <xsl:element name="graphics" namespace="uri:poosl">
          <xsl:element name="node" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="5"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="5"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:for-each select="$clusterClassNode/po:instance/po:connection[@channel=$channelName]">
            <xsl:variable name="instanceName" select="../@name"/>
            <xsl:variable name="portName" select="@port"/>
            <xsl:element name="segment" namespace="uri:poosl">
              <xsl:element name="node" namespace="uri:poosl">
                <xsl:attribute name="number">
                  <xsl:value-of select="1"/>
                </xsl:attribute>
              </xsl:element>
              <xsl:element name="instance_port" namespace="uri:poosl">
                <xsl:attribute name="instance_name">
                  <xsl:value-of select="$instanceName"/>
                </xsl:attribute>
                <xsl:attribute name="port_name">
                  <xsl:value-of select="$portName"/>
                </xsl:attribute>
              </xsl:element>
            </xsl:element>
          </xsl:for-each>
          <xsl:if test="@output_port">
            <xsl:element name="segment" namespace="uri:poosl">
              <xsl:element name="node" namespace="uri:poosl">
                <xsl:attribute name="number">
                  <xsl:value-of select="1"/>
                </xsl:attribute>
              </xsl:element>
              <xsl:element name="cluster_port" namespace="uri:poosl">
                <xsl:attribute name="port_name">
                  <xsl:value-of select="@output_port"/>
                </xsl:attribute>
              </xsl:element>
            </xsl:element>

          </xsl:if>
          <xsl:element name="name_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="100"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="100"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="message_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="200"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="200"/>
            </xsl:attribute>
            <xsl:attribute name="width">
              <xsl:value-of select="50"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="scenario" namespace="uri:poosl">
            <xsl:attribute name="name">normal</xsl:attribute>
          </xsl:element>

        </xsl:element>
      </xsl:element>
    </xsl:element>

  </xsl:template>

  <xsl:template match="po:instance">
    <xsl:element name="instance" namespace="uri:poosl">
      <xsl:attribute name="type">
        <xsl:value-of select="@type"/>
      </xsl:attribute>
      <xsl:attribute name="class">
        <xsl:value-of select="@class"/>
      </xsl:attribute>
      <xsl:attribute name="name">
        <xsl:value-of select="@name"/>
      </xsl:attribute>
      <xsl:for-each select="po:instantiation_expression">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:for-each select="po:connection">
        <xsl:apply-templates select="."/>
      </xsl:for-each>
      <xsl:element name="annotation" namespace="uri:poosl">
        <xsl:element name="graphics" namespace="uri:poosl">
          <xsl:element name="box" namespace="uri:poosl">
            <xsl:attribute name="left">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="top">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="right">
              <xsl:value-of select="100"/>
            </xsl:attribute>
            <xsl:attribute name="bottom">
              <xsl:value-of select="100"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="name_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="scenario" namespace="uri:poosl">
            <xsl:attribute name="name">normal</xsl:attribute>
          </xsl:element>
        </xsl:element>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template match="po:connection">
    <xsl:element name="connection" namespace="uri:poosl">
      <xsl:attribute name="port">
        <xsl:value-of select="@port"/>
      </xsl:attribute>
      <xsl:if test="@channel">
        <xsl:attribute name="channel">
          <xsl:value-of select="@channel"/>
        </xsl:attribute>
      </xsl:if>
      <xsl:element name="annotation" namespace="uri:poosl">
        <xsl:element name="graphics" namespace="uri:poosl">
          <xsl:element name="location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="name_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="5"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
        </xsl:element>
      </xsl:element>
    </xsl:element>
  </xsl:template>

  <!-- called templates below -->

  <xsl:template name="annotate_port">
    <xsl:element name="port" namespace="uri:poosl">
      <xsl:attribute name="name">
        <xsl:value-of select="@name"/>
      </xsl:attribute>
      <xsl:for-each select="po:message">
        <xsl:copy-of select="."/>
      </xsl:for-each>
      <xsl:element name="annotation" namespace="uri:poosl">
        <xsl:element name="graphics" namespace="uri:poosl">
          <xsl:element name="location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
          <xsl:element name="name_location" namespace="uri:poosl">
            <xsl:attribute name="x">
              <xsl:value-of select="0"/>
            </xsl:attribute>
            <xsl:attribute name="y">
              <xsl:value-of select="0"/>
            </xsl:attribute>
          </xsl:element>
        </xsl:element>

      </xsl:element>
    </xsl:element>
  </xsl:template>

  <xsl:template name="process_method" xml:space="default">
    <xsl:choose>
      <xsl:when test="po:body_text">
        <xsl:for-each select="po:body_text">
          <xsl:value-of select="."/>
        </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="@name"/>
        <xsl:text>(</xsl:text>
        <xsl:for-each select="po:argument">
          <xsl:if test="position() &gt; 1">
            <xsl:text>, </xsl:text>
          </xsl:if>
          <xsl:value-of select="@name"/>:<xsl:value-of select="@type"/>
        </xsl:for-each>
        <xsl:text>)(</xsl:text>
        <xsl:for-each select="po:output_variable">
          <xsl:if test="position() &gt; 1">
            <xsl:text>, </xsl:text>
          </xsl:if>
          <xsl:value-of select="@name"/>:<xsl:value-of select="@type"/>
        </xsl:for-each>
        <xsl:text>)</xsl:text>
        <xsl:call-template name="newline" />
        <xsl:if test="count(po:local_variable)>0">
          <xsl:text>| </xsl:text>
          <xsl:for-each select="po:local_variable">
            <xsl:if test="position()>1">
              <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:value-of select="@name"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="@type"/>
          </xsl:for-each>
          <xsl:text> |</xsl:text>
          <xsl:call-template name="newline" />
        </xsl:if>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="1" />
        </xsl:call-template>
        <xsl:for-each select="po:body_statement/*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="1" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text>.</xsl:text>
        <xsl:call-template name="newline" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="statement">
    <xsl:param name="indentation_level" />
    <xsl:variable name="nodeType" select="name(self::node())" />
    <xsl:choose>
      <xsl:when test="$nodeType='sequence_of_statements'">
        <xsl:for-each select="po:*">
          <xsl:if test="position() &gt; 1">
            <xsl:text>;</xsl:text>
            <xsl:call-template name="newline">
              <xsl:with-param name="indentation_level" select="$indentation_level"/>
            </xsl:call-template>
          </xsl:if>
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level"/>
          </xsl:call-template>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='parallel_statements'">
        <xsl:text>par</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
        </xsl:call-template>
        <xsl:for-each select="po:*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level"/>
          </xsl:call-template>
          <xsl:if test="position() &lt; last()">
            <xsl:text>and</xsl:text>
            <xsl:call-template name="newline">
              <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
            </xsl:call-template>
          </xsl:if>
        </xsl:for-each>
        <xsl:text>rap</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='select_statements'">
        <xsl:text>sel</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
        </xsl:call-template>
        <xsl:for-each select="po:*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level"/>
          </xsl:call-template>
          <xsl:if test="position() &lt; last()">
            <xsl:text>or</xsl:text>
            <xsl:call-template name="newline">
              <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
            </xsl:call-template>
          </xsl:if>
        </xsl:for-each>
        <xsl:text>les</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='expression'">
        <xsl:for-each select="po:*">
          <xsl:variable name="requires_atomic">
            <xsl:call-template name="requires_atomic"/>
          </xsl:variable>
          <xsl:variable name="new_indentation_level">
            <xsl:choose>
              <xsl:when test="$requires_atomic = 'true'">
                <xsl:value-of select="$indentation_level + 1"/>
              </xsl:when>
              <xsl:otherwise>
                <xsl:value-of select="$indentation_level"/>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:variable>
          <xsl:if test="$requires_atomic = 'true'">
            <xsl:text>{</xsl:text>
            <xsl:call-template name="newline">
              <xsl:with-param name="indentation_level" select="$new_indentation_level"/>
            </xsl:call-template>
          </xsl:if>
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="false"/>
            <xsl:with-param name="indentation_level" select="$new_indentation_level"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
          <xsl:if test="$requires_atomic = 'true'">
            <xsl:call-template name="newline">
              <xsl:with-param name="indentation_level" select="$indentation_level"/>
            </xsl:call-template>
            <xsl:text>}</xsl:text>
          </xsl:if>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='process_method_call'">
        <xsl:value-of select="@method_name"/>
        <xsl:text>(</xsl:text>
        <xsl:for-each select="po:argument/*">
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
            <xsl:with-param name="horizontal" select="true"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text>)(</xsl:text>
        <xsl:for-each select="po:return_variable/*">
          <xsl:value-of select="./text()"/>
        </xsl:for-each>
        <xsl:text>)</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='message_send'">
        <xsl:value-of select="po:port/text()"/>
        <xsl:text>!</xsl:text>
        <xsl:value-of select="@message_name"/>
        <xsl:if test="po:argument">
          <xsl:text>(</xsl:text>
          <xsl:for-each select="po:argument/*">
            <xsl:if test="position() &gt; 1">
              <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:call-template name="expression">
              <xsl:with-param name="horizontal" select="true"/>
              <xsl:with-param name="indentation_level" select="0"/>
              <xsl:with-param name="binding" select="3"/>
            </xsl:call-template>
          </xsl:for-each>
          <xsl:text>)</xsl:text>
        </xsl:if>
        <xsl:for-each select="po:atomic_expression/*">
          <xsl:text>{ </xsl:text>
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="true"/>
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
          <xsl:text> }</xsl:text>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='message_receive'">
        <xsl:value-of select="po:port/text()"/>
        <xsl:text>?</xsl:text>
        <xsl:value-of select="@message_name"/>
        <xsl:if test="po:variable">
          <xsl:text>(</xsl:text>
          <xsl:for-each select="po:variable">
            <xsl:if test="position() &gt; 1">
              <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:value-of select="text()"/>
          </xsl:for-each>
          <xsl:for-each select="po:condition/*">
            <xsl:text> | </xsl:text>
            <xsl:call-template name="expression">
              <xsl:with-param name="horizontal" select="true"/>
              <xsl:with-param name="indentation_level" select="0"/>
              <xsl:with-param name="binding" select="3"/>
            </xsl:call-template>
          </xsl:for-each>
          <xsl:text>)</xsl:text>
        </xsl:if>
        <xsl:for-each select="po:atomic_expression/*">
          <xsl:text>{ </xsl:text>
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="true"/>
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
          <xsl:text> }</xsl:text>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='guard'">
        <xsl:text>[</xsl:text>
        <xsl:for-each select="po:expression/*">
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="true"/>
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text>] </xsl:text>
        <xsl:for-each select="po:statement/*">
          <xsl:variable name="isVertical">
            <xsl:call-template name="statement_is_vertical"/>
          </xsl:variable>
          <xsl:choose>
            <xsl:when test="$isVertical = 'true'">
              <xsl:call-template name="newline">
                <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
              </xsl:call-template>
              <xsl:call-template name="statement">
                <xsl:with-param name="indentation_level" select="$indentation_level+1"/>
              </xsl:call-template>
            </xsl:when>
            <xsl:otherwise>
              <xsl:call-template name="statement">
                <xsl:with-param name="indentation_level" select="$indentation_level"/>
              </xsl:call-template>
            </xsl:otherwise>
          </xsl:choose>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='if'">
        <xsl:text>if </xsl:text>
        <xsl:for-each select="po:condition/*">
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="true"/>
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text> then</xsl:text>
        <xsl:for-each select="po:then/*">
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:for-each select="po:else/*">
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level"/>
          </xsl:call-template>
          <xsl:text>else</xsl:text>
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level"/>
        </xsl:call-template>
        <xsl:text>fi</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='while'">
        <xsl:text>while </xsl:text>
        <xsl:for-each select="po:condition/*">
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="true"/>
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text> do</xsl:text>
        <xsl:for-each select="po:body/*">
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level"/>
        </xsl:call-template>
        <xsl:text>od</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='abort'">
        <xsl:text>abort (</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
        </xsl:call-template>
        <xsl:for-each select="po:normal_behavior/*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level"/>
        </xsl:call-template>
        <xsl:text>) with (</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
        </xsl:call-template>
        <xsl:for-each select="po:aborting_behavior/*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level"/>
        </xsl:call-template>
        <xsl:text>)</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='interrupt'">
        <xsl:text>interrupt (</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
        </xsl:call-template>
        <xsl:for-each select="po:normal_behavior/*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level"/>
        </xsl:call-template>
        <xsl:text>) with (</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
        </xsl:call-template>
        <xsl:for-each select="po:interrupting_behavior/*">
          <xsl:call-template name="statement">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level"/>
        </xsl:call-template>
        <xsl:text>)</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='skip'">
        <xsl:text>skip</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='delay'">
        <xsl:text>delay </xsl:text>
        <xsl:for-each select="po:*">
          <xsl:call-template name="expression">
            <xsl:with-param name="horizontal" select="true"/>
            <xsl:with-param name="indentation_level" select="0"/>
            <xsl:with-param name="binding" select="3"/>
          </xsl:call-template>
        </xsl:for-each>
      </xsl:when>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="statement_is_vertical">
    <xsl:variable name="nodeType" select="name(self::node())" />
    <xsl:choose>
      <xsl:when test="$nodeType='sequence_of_statements'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='parallel_statements'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='select_statements'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='expression'">
        <xsl:for-each select="po:*">
          <xsl:call-template name="requires_atomic" />
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='process_method_call'">
        <xsl:value-of select="'false'"/>
      </xsl:when>
      <xsl:when test="$nodeType='message_send'">
        <xsl:value-of select="'false'"/>
      </xsl:when>
      <xsl:when test="$nodeType='message_receive'">
        <xsl:value-of select="'false'"/>
      </xsl:when>
      <xsl:when test="$nodeType='guard'">
        <xsl:for-each select="po:statement">
          <xsl:call-template name="statement_is_vertical"/>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='if'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='while'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='abort'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='interrupt'">
        <xsl:value-of select="'true'"/>
      </xsl:when>
      <xsl:when test="$nodeType='skip'">
        <xsl:value-of select="'false'"/>
      </xsl:when>
      <xsl:when test="$nodeType='delay'">
        <xsl:value-of select="'false'"/>
      </xsl:when>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="requires_atomic">
    <xsl:value-of select="name(self::node()) = 'sequence_of_expressions'"/>
  </xsl:template>

  <xsl:template name="data_method" xml:space="default">

    <xsl:choose>
      <xsl:when test="po:body_text">
        <xsl:for-each select="po:body_text">
          <xsl:value-of select="."/>
        </xsl:for-each>
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="@name"/>
        <xsl:text>(</xsl:text>
        <xsl:for-each select="po:argument">
          <xsl:if test="position() &gt; 1">
            <xsl:text>, </xsl:text>
          </xsl:if>
          <xsl:value-of select="@name"/>:<xsl:value-of select="@type"/>
        </xsl:for-each>
        <xsl:text>): </xsl:text>
        <xsl:value-of select="po:return_type/text()"/>
        <xsl:call-template name="newline" />
        <xsl:if test="count(po:local_variable)>0">
          <xsl:text>| </xsl:text>
          <xsl:for-each select="po:local_variable">
            <xsl:if test="position()>1">
              <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:value-of select="@name"/>
            <xsl:text>: </xsl:text>
            <xsl:value-of select="@type"/>
          </xsl:for-each>
          <xsl:text> |</xsl:text>
          <xsl:call-template name="newline" />
        </xsl:if>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="1" />
        </xsl:call-template>
        <xsl:for-each select="po:body_expression/*">
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="1" />
            <xsl:with-param name="horizontal_mode" select="0" />
            <xsl:with-param name="binding_level" select="0" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text>.</xsl:text>
        <xsl:call-template name="newline" />
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="expression">
    <xsl:param name="indentation_level" />
    <xsl:param name="horizontal_mode" />
    <xsl:param name="binding_level" />
    <xsl:variable name="nodeType" select="name(self::node())" />
    <xsl:choose>
      <xsl:when test="$nodeType='sequence_of_expressions'">
        <xsl:for-each select="./*">
          <xsl:if test="position()>1">
            <xsl:text>;</xsl:text>
            <xsl:call-template name="newline">
              <xsl:with-param name="indentation_level" select="$indentation_level"/>
            </xsl:call-template>
          </xsl:if>
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
          </xsl:call-template>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='assignment'">
        <xsl:value-of select="@variable_name"/>
        <xsl:text> := </xsl:text>
        <xsl:for-each select="*">
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:for-each>
      </xsl:when>
      <xsl:when test="$nodeType='data_method_call'">
        <xsl:for-each select="po:receiver/*">
          <xsl:variable name="receiver_binding">
            <xsl:call-template name="binding_level"/>
          </xsl:variable>
          <xsl:if test="$receiver_binding &lt; 4">
            <xsl:text>(</xsl:text>
          </xsl:if>
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
          <xsl:if test="$receiver_binding &lt; 4">
            <xsl:text>)</xsl:text>
          </xsl:if>
        </xsl:for-each>
        <xsl:text> </xsl:text>
        <xsl:if test="@super = 'true'">
          <xsl:text>^</xsl:text>
        </xsl:if>
        <xsl:value-of select="@method_name"/>
        <xsl:if test="count(po:argument)>0">
          <xsl:text>(</xsl:text>
          <xsl:for-each select="po:argument">
            <xsl:if test="position()>1">
              <xsl:text>, </xsl:text>
            </xsl:if>
            <xsl:for-each select="*">
              <xsl:call-template name="expression" >
                <xsl:with-param name="indentation_level" select="$indentation_level" />
                <xsl:with-param name="horizontal" select="true" />
              </xsl:call-template>
            </xsl:for-each>
          </xsl:for-each>
          <xsl:text>)</xsl:text>
        </xsl:if>
      </xsl:when>
      <xsl:when test="$nodeType='constant'">
        <xsl:value-of select="text()"/>
      </xsl:when>
      <xsl:when test="$nodeType='variable'">
        <xsl:value-of select="@name"/>
      </xsl:when>
      <xsl:when test="$nodeType='binary_operation'">
        <xsl:call-template name="binary_operation">
          <xsl:with-param name="binding" select="0" />
          <xsl:with-param name="leftop" select="./po:left_operand/*"/>
          <xsl:with-param name="rightop" select="./po:right_operand/*"/>
          <xsl:with-param name="operator" select="@operator"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$nodeType='unary_operation'">
        <xsl:call-template name="unary_operation">
          <xsl:with-param name="binding" select="0" />
          <xsl:with-param name="operand" select="./po:operand/*"/>
          <xsl:with-param name="operator" select="@operator"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$nodeType='unary_operation'">
        <xsl:value-of select="@operator"/>
        <xsl:text>(</xsl:text>
        <xsl:for-each select="operand/*">
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text>) </xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='self'">
        <xsl:text>self</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='nil'">
        <xsl:text>nil</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='current_time'">
        <xsl:text>currentTime</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='if'">
        <xsl:text>if </xsl:text>
        <xsl:for-each select="po:condition/*">
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text> then</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1" />
        </xsl:call-template>
        <xsl:for-each select="po:then/*">
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
          </xsl:call-template>
        </xsl:for-each>
        <xsl:if test="count(po:else)>0">
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level" />
          </xsl:call-template>
          <xsl:text>else</xsl:text>
          <xsl:call-template name="newline">
            <xsl:with-param name="indentation_level" select="$indentation_level + 1" />
          </xsl:call-template>
          <xsl:for-each select="po:else/*">
            <xsl:call-template name="expression">
              <xsl:with-param name="indentation_level" select="$indentation_level + 1"/>
            </xsl:call-template>
          </xsl:for-each>
        </xsl:if>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level" />
        </xsl:call-template>
        <xsl:text>fi</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='while'">
        <xsl:text>while </xsl:text>
        <xsl:for-each select="po:condition/*">
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text> do</xsl:text>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level + 1" />
        </xsl:call-template>
        <xsl:for-each select="po:body/*">
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level + 1" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:call-template name="newline">
          <xsl:with-param name="indentation_level" select="$indentation_level" />
        </xsl:call-template>
        <xsl:text>od</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='new'">
        <xsl:text>new (</xsl:text>
        <xsl:value-of select="@data_class"/>
        <xsl:text>)</xsl:text>
      </xsl:when>
      <xsl:when test="$nodeType='return'">
        <xsl:text>return(</xsl:text>
        <xsl:for-each select="*">
          <xsl:call-template name="expression" >
            <xsl:with-param name="indentation_level" select="$indentation_level" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:for-each>
        <xsl:text>)</xsl:text>
      </xsl:when>

    </xsl:choose>
  </xsl:template>

  <xsl:template name="binary_operation">
    <xsl:param name="leftop"/>
    <xsl:param name="rightop"/>
    <xsl:param name="operator"/>
    <xsl:param name="indentation_level"/>
    <xsl:choose>
      <xsl:when test="$operator = 'add'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> + </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="1"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'equal'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> = </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'and'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> &amp; </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="1"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'or'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> | </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="1"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'less_than'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> &lt; </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'greater_than'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> &gt; </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'identical'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> == </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'not_identical'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> !== </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'subtract'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> - </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="1"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'multiply'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> * </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="2"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'divide'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> / </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="2"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'unequal'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> != </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'at_least'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> &gt;= </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'at_most'">
        <xsl:call-template name="binop_output">
          <xsl:with-param name="operator">
            <xsl:text> &lt;= </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="0"/>
          <xsl:with-param name="leftop" select="$leftop"/>
          <xsl:with-param name="rightop" select="$rightop"/>
        </xsl:call-template>
      </xsl:when>
    </xsl:choose>

  </xsl:template>

  <xsl:template name="unary_operation">
    <xsl:param name="operand"/>
    <xsl:param name="operator"/>
    <xsl:param name="indentation_level"/>
    <xsl:choose>
      <xsl:when test="$operator = 'minus'">
        <xsl:call-template name="unop_output">
          <xsl:with-param name="operator">
            <xsl:text> - </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="5"/>
          <xsl:with-param name="operand" select="$operand"/>
        </xsl:call-template>
      </xsl:when>
      <xsl:when test="$operator = 'bit_not'">
        <xsl:call-template name="unop_output">
          <xsl:with-param name="operator">
            <xsl:text> ! </xsl:text>
          </xsl:with-param>
          <xsl:with-param name="binding" select="5"/>
          <xsl:with-param name="operand" select="$operand"/>
        </xsl:call-template>
      </xsl:when>
    </xsl:choose>

  </xsl:template>

  <xsl:template name="binop_output">
    <xsl:param name="operator"/>
    <xsl:param name="binding"/>
    <xsl:param name="leftop"/>
    <xsl:param name="rightop"/>
    <xsl:for-each select="$leftop">
      <xsl:variable name="opbinding">
        <xsl:call-template name="binding_level"/>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="$binding > $opbinding">
          <xsl:text>(</xsl:text>
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
          <xsl:text>)</xsl:text>
        </xsl:when>
        <xsl:otherwise>
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:for-each>
    <xsl:value-of select="$operator"/>
    <xsl:for-each select="$rightop">
      <xsl:variable name="opbinding">
        <xsl:call-template name="binding_level"/>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="$binding >= $opbinding">
          <xsl:text>(</xsl:text>
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
          <xsl:text>)</xsl:text>
        </xsl:when>
        <xsl:otherwise>
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:for-each>
  </xsl:template>

  <xsl:template name="unop_output">
    <xsl:param name="operator"/>
    <xsl:param name="binding"/>
    <xsl:param name="operand"/>

    <xsl:value-of select="$operator"/>
    <xsl:for-each select="$operand">
      <xsl:variable name="opbinding">
        <xsl:call-template name="binding_level"/>
      </xsl:variable>
      <xsl:choose>
        <xsl:when test="$binding >= $opbinding">
          <xsl:text>(</xsl:text>
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
          <xsl:text>)</xsl:text>
        </xsl:when>
        <xsl:otherwise>
          <xsl:call-template name="expression">
            <xsl:with-param name="indentation_level" select="0" />
            <xsl:with-param name="horizontal" select="true" />
          </xsl:call-template>
        </xsl:otherwise>
      </xsl:choose>
    </xsl:for-each>
  </xsl:template>

  <!-- returns precedence level (0-3), 0=lowest, 3=highest  -->
  <xsl:template name="binding_level">
    <xsl:variable name="nodeType" select="name(self::node())" />
    <xsl:choose>
      <xsl:when test="$nodeType='binary_operation'">
        <xsl:call-template name="binding_level_binop"/>
      </xsl:when>
      <xsl:when test="$nodeType='unary_operation'">
        <xsl:call-template name="binding_level_unop" />
      </xsl:when>
      <xsl:otherwise>
        <xsl:value-of select="4"/>
      </xsl:otherwise>
    </xsl:choose>
  </xsl:template>

  <!-- returns precedence level (0-3), 0=lowest, 3=highest  -->
  <xsl:template name="binding_level_binop">
    <xsl:variable name="operator" select="@operator" />
    <xsl:choose>
      <xsl:when test="$operator = 'add'">
        <xsl:value-of select="1"/>
      </xsl:when>
      <xsl:when test="$operator = 'equal'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'and'">
        <xsl:value-of select="1"/>
      </xsl:when>
      <xsl:when test="$operator = 'or'">
        <xsl:value-of select="1"/>
      </xsl:when>
      <xsl:when test="$operator = 'less_than'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'greater_than'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'subtract'">
        <xsl:value-of select="1"/>
      </xsl:when>
      <xsl:when test="$operator = 'multiply'">
        <xsl:value-of select="2"/>
      </xsl:when>
      <xsl:when test="$operator = 'divide'">
        <xsl:value-of select="2"/>
      </xsl:when>
      <xsl:when test="$operator = 'unequal'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'identical'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'not_identical'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'less_than'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'greater_than'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'at_least'">
        <xsl:value-of select="0"/>
      </xsl:when>
      <xsl:when test="$operator = 'at_most'">
        <xsl:value-of select="0"/>
      </xsl:when>
    </xsl:choose>
  </xsl:template>

  <!-- returns precedence level (0-3), 0=lowest, 3=highest  -->
  <xsl:template name="binding_level_unop">
    <xsl:variable name="operator" select="@operator" />
    <xsl:choose>
      <xsl:when test="$operator = 'minus'">
        <xsl:value-of select="5"/>
      </xsl:when>
    </xsl:choose>
  </xsl:template>

  <xsl:template name="newline">
    <xsl:param name="indentation_level"/>
    <xsl:text>&#10;</xsl:text>
    <xsl:call-template name="write_tabs">
      <xsl:with-param name="number" select="$indentation_level"/>
    </xsl:call-template>
  </xsl:template>

  <xsl:template name="write_tabs">
    <xsl:param name="number"/>
    <xsl:if test="$number &gt; 0">
      <xsl:text>  </xsl:text>
      <xsl:call-template name="write_tabs">
        <xsl:with-param name="number" select="$number - 1"/>
      </xsl:call-template>
    </xsl:if>
  </xsl:template>

</xsl:stylesheet>

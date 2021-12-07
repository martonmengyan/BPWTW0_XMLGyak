<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<xsl:template match="/">
		<html>
			<body>
				<h2>Orarend</h2>
				<table border="1">
					<tr bgcolor="#375b8c">
						<th>targy</th>
						<th>tipus</th>
						<th>idopont</th>
						<th>helyszin</th>
						<th>oktato</th>
						<th>szak</th>
					</tr>
					<xsl:for-each select="orarend/ora">
						<tr>
							<td><xsl:value-of select="targy"></xsl:value-of></td>
							<td><xsl:value-of select="@tipus"/></td>
							<td><xsl:value-of select="idopont/nap"/>-<xsl:value-of select="idopont/tol"/>-<xsl:value-of select="idopont/ig"/></td>
							<td><xsl:value-of select="helyszin"/></td>
							<td><xsl:value-of select="oktato"></xsl:value-of></td>
							<td><xsl:value-of select="szak"></xsl:value-of></td>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
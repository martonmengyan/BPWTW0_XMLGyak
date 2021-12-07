<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Hallgatok adatai</h2>
				<table border="4">
					<tr bgcolor="#607d9e">
						<th>ID</th>
						<th>Vezeteknev</th>
						<th>Keresztnev</th>
						<th>Becenev</th>
						<th>Fizetes</th>
						<th>Fokozat</th>
					</tr>
					<xsl:for-each select="class/student[@id]">
						<tr>
							<td><xsl:value-of select="@id" /></td>
							<td><xsl:value-of select="vezeteknev" /></td>
							<td><xsl:value-of select="keresztnev" /></td>
							<td><xsl:value-of select="becenev" /></td>
							<td><xsl:value-of select="fizetes" /></td>

							<xsl:choose>
								<xsl:when test="fizetes > 500000">
									<td>Magas</td>
								</xsl:when>
								<xsl:when test="fizetes > 400000">
									<td>Kozepes</td>
								</xsl:when>
								<xsl:otherwise>
									<td>Alacsony</td>
								</xsl:otherwise>
							</xsl:choose>
						</tr>
					</xsl:for-each>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
# Condominio

Condominio
	Post-> http://localhost:8081/api/condominios
	Get All -> http://localhost:8081/api/condominios
-----------------------------------------------------------------------------------------------

Unidade	
	Post -> http://localhost:8081/api/condominios/{condominioId}/unidades
	Get All -> http://localhost:8081/api/unidades	
-----------------------------------------------------------------------------------------------
Multas	
	Post => http://localhost:8081/api/condominios/{condominioId}/unidades/{unidadeId}/multas	
	Get => http://localhost:8081/api/condominios/{condominioId}/multas
	
Listar Multas 
	Get com multas => http://localhost:8081/api/unidadades/commultas
	Get sem multas => http://localhost:8081/api/unidadades/semultas

--------------------------------------------------------------------------------------------------

Avisos
	Post -> http://localhost:8081/api/condominios/{condominioId}/unidades/{unidadeId}/avisos
	Get -> http://localhost:8081/api/condominios/{condominioId}/avisos
---------------------------------------------------------------------------------------------------

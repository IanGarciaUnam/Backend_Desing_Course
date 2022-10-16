# Bugs observados durante el desarrollo:
1.-ConstraintViolationException
Al parecer esta excepción no captura el error dado que es:java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'MXSopa' for key 'product.gtin'
que bien deriva en ´DataIntegrityViolationException´, por lo cual la agregué y dejé el ConstraintViolationException anterior para capturar cualquier excepción que pudiera estar relacionada con él

2.- Sobre createProduct
El createProduct implementado la última vez, es eficiente y de corto código, pero no permite la activación de los productos, asimismo fue necesario implementar un método que regresará los un elemento de la bd sin importar su estatus

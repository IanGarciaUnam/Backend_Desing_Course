# Bugs observados durante el desarrollo:
1.-ConstraintViolationException
Al parecer esta excepción no captura el error dado que es:java.sql.SQLIntegrityConstraintViolationException: Duplicate entry 'MXSopa' for key 'product.gtin'
que bien deriva en ´DataIntegrityViolationException´, por lo cual la agregué y dejé el ConstraintViolationException anterior para capturar cualquier excepción que pudiera estar relacionada con él

2.- Sobre createProduct
El createProduct implementado la última vez, es eficiente y de corto código, pero no permite la activación de los productos, asimismo fue necesario implementar un método que regresará los un elemento de la bd sin importar su estatus  

4. Sobre ListProducts de categorías desactivadas
Y no se específica el caso en que una categoría este desactivada y se pida su lista de productos, dado por el problema anterior, para resolverlo solo agregamos una validación que evite este conflicto

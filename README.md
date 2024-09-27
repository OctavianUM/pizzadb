# Databases project: pizza delivery
## exporting the database
`mysqldump --add-drop-table -u root -p pizzadb > src\main\java\com\dbproject\resources\pizzadb.sql`

## importing the database
`mysql -u root -p pizzadb < src\main\java\com\dbproject\resources\pizzadb.sql` 
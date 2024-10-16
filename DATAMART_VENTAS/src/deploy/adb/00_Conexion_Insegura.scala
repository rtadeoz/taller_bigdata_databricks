// Databricks notebook source
// Configurar las variables de conexión
val storageAccountName = "adlesping09eu02desa01db"
val containerName = "lakehousedata"
val accessKey = "qPrS8A5XSA/EU4UymbIo5B8cvBeo1r1i9z41uEpTACMYjfelG9f/ZjwglY9mloBZpClCSDaYsW0I+AStpXYP9w=="

// COMMAND ----------

// Configurar las opciones de Spark para acceder al almacenamiento
spark.conf.set(s"fs.azure.account.key.$storageAccountName.blob.core.windows.net", accessKey)

spark.conf.set(s"fs.azure.account.key.$storageAccountName.dfs.core.windows.net", accessKey)

// COMMAND ----------

dbutils.fs.ls(s"abfss://$containerName@$storageAccountName.dfs.core.windows.net/tmp/")

// COMMAND ----------

// Leer datos desde el almacenamiento
val filePath = s"wasbs://$containerName@$storageAccountName.blob.core.windows.net/tmp/cliente/persona.csv"

val df_cliente = spark.read.format("csv").option("header", "true").option("delimiter", "|").load(filePath)

// COMMAND ----------

// Mostrar el DataFrame
df_cliente.show()

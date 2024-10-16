// Databricks notebook source
dbutils.secrets.listScopes()

// COMMAND ----------

// Configurar las variables de conexión
val secretScope = "dmc-secret-scope3"
val storageAccountName = dbutils.secrets.get(scope = secretScope, key = "adls-name")
val containerName = dbutils.secrets.get(scope = secretScope, key = "adls-container")
val accessKey = dbutils.secrets.get(scope = secretScope, key = "adls-access-key")

// COMMAND ----------

// Configurar las opciones de Spark para acceder al almacenamiento
spark.conf.set(s"fs.azure.account.key.$storageAccountName.dfs.core.windows.net", accessKey)

// COMMAND ----------

// List the files in the specified directory
dbutils.fs.ls("abfss://lakehousedata@adlesping09eu02desa01db.dfs.core.windows.net/bronze/")

// COMMAND ----------

// Leer datos desde el almacenamiento
val filePath = s"abfss://$containerName@$storageAccountName.dfs.core.windows.net/tmp/cliente/persona.csv"

val df_cliente = spark.read.format("csv").option("header", "true").option("delimiter", "|").load(filePath)

// COMMAND ----------

// Mostrar el DataFrame
display(df_cliente)

def PRM_AMBIENTE="desa"
echo "Mi ambiente: ${PRM_AMBIENTE}"

node {
    def workspace = env.WORKSPACE
    echo "workspace actual: ${workspace}"
    
    stage('Inicio') {
    echo "Inicio de Pipeline"
    }
    
    stage('Preparacion') {
    echo "Conexion repositorio remoto GIT HUB"
    checkout scm
    sh "pwd"
    sh "ls"
    }
    
    stage('Build') {
    echo "Compilando codigo fuente"
    }
    
    stage('Execute QA') {
    echo "Ejecucion de pruebas de calidad"
    }
    
    stage('Execute Sast') {
    echo "Ejecucion de pruebas de seguridad"
    }
    
    stage('Upload Artifact') {
    echo "Carga de artefacto en artifactory"
    }
    
    stage('Deploy in Azure Databricks') {
    echo "Despliegue en Databricks"
    sh "databricks workspace mkdirs /${PRM_AMBIENTE}/corp/finanzas/datamart_ventas/ --profile JENKINS"
    sh "databricks workspace import /${PRM_AMBIENTE}/corp/finanzas/datamart_ventas/01_Bronze.py --file ${workspace}/DATAMART_VENTAS/src/deploy/dbfs/01_Bronze.py  --format SOURCE --language PYTHON --profile JENKINS"       
    sh "databricks workspace import /${PRM_AMBIENTE}/corp/finanzas/datamart_ventas/02_Silver.sql --file ${workspace}/DATAMART_VENTAS/src/deploy/dbfs/02_Silver.sql  --format SOURCE --language SQL --profile JENKINS"      
    sh "databricks workspace import /${PRM_AMBIENTE}/corp/finanzas/datamart_ventas/03_Gold.sql --file ${workspace}/DATAMART_VENTAS/src/deploy/dbfs/03_Gold.sql  --format SOURCE --language SQL --profile JENKINS"      
    sh "databricks workspace import /${PRM_AMBIENTE}/corp/finanzas/datamart_ventas/00_Montar_Sistema_Archivos.scala --file ${workspace}/DATAMART_VENTAS/src/deploy/dbfs/00_Montar_Sistema_Archivos.scala  --format SOURCE --language SCALA --profile JENKINS"      
    }
   
}

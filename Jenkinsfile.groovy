def PRM_AMBIENTE="DESA"
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
    sh "databricks workspace import /prod/datamart_ventas/01_Bronze.py --file ${workspace}/01_Bronze.py  --format SOURCE --language PYTHON --profile JENKINS"       
    }
   
}

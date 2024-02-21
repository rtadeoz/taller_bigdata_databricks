def PRM_AMBIENTE="DESA"
echo "Mi ambiente: ${PRM_AMBIENTE}"

node {
    def workspace = env.WORKSPACE
    echo "workspace actual: ${workspace}"
    
    stage('Inicio') {
    echo "Inicio de Pipeline"
    }
    
    stage('Preparacion') {
    echo "Conexion repositorio remot GIT HUB"
    checkout scm
    sh "pwd"
    sh "ls"
    }
    
    // **sh "databricks workspace import /Users/rickt89@gmail.com/test/00_Montar_Sistema_Archivos.dbc --file /var/lib/jenkins/workspace/JOB_PIPELINE_CI_CD/00_Montar_Sistema_Archivos.dbc --profile JENKINS"   
}

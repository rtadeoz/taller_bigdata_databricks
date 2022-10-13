def PRM_AMBIENTE="DESA"
echo "Mi ambiente: ${PRM_AMBIENTE}"

node {
    def workspace = env.WORKSPACE
    echo "workspace actual: ${workspace}"
    
    stage('Inicio') {
    echo "Inicio de Pipeline"
    }
    
    stage('Preparacion') {
    echo "conexion repo"
    checkout scm
    sh "pwd"
    sh "ls"
    }
}
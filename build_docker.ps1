# Relative path to the file
$filePath = "target/service.jar"

# Check if the file exists
if (-Not (Test-Path $filePath)) {
    # Write an error message and exit the script
    Write-Error "The file '$filePath' does not exist. Please build the project first."
    exit 1
}

docker build -t llm-server .
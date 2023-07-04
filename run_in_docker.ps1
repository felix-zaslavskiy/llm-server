# Variables
$localModelDirectory = "C:\Users\felix\AppData\Local\nomic.ai\GPT4All\" # Replace with the path to your local directory
$modelFilename = "ggml-replit-code-v1-3b.bin" # Replace with the name of the file

# Run Docker container
docker run -p 8083:8080 -v ${localModelDirectory}:/opt/app/config -e "MODEL_FILE_NAME=${modelFilename}" llm-server
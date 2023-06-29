# Large Language Model Server

## Motivation
This web server application will execute text generations very similar to how OpenAI API works.

1. Generate text based on a prompt.
2. Generate a Chat Completion with a bit more structure.

Underlying technology is GPT4ALL backend that provides the LLM function.

The server will serve as an administration layer that organization would typically need 
in order to allow access to large language models to various internal clients.
Some ideas on what this may be used for:
- Authentication of clients to make sure we know who is accessing the LLM.
- Monitoring of usage so offensive or other inappropriate prompts for example may be detected and admin alerted.
- Mange the LLM resource based on various factors. Rate limits and such things.
- Provide organization a central point of how LLMs are being utilized by different applications.

## Getting Started
After starting up the web server you may navigate for example to the 
api endpoint to try things out http://localhost:8080/swagger-ui/index.htm


## Technical issues.
The server currently loads one main model that. Typically,this may be the best performing model for whatever usecase 
an organization may be using this server for.

It is not totally clear if multiple models should be supported.

It is not clear how memory usage is impacted when multiple users will be using the LLM.

## Dev notes:
Swagger and OpenAPI docs here https://springdoc.org/v2/#getting-started
## TODO:
* Dockerfile to build a docker image.
* Implement Chat API
* List models endpoint for convenience.

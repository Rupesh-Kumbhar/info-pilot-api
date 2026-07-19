## Why This Project?

Organizations store critical information across SOPs, technical documentation, user manuals, incident reports, and knowledge articles. Employees often spend significant time searching through these documents to find relevant information, which can reduce productivity and increase resolution times.

The Enterprise Knowledge Assistant aims to solve this problem by leveraging Retrieval-Augmented Generation (RAG) and Large Language Models (LLMs). Users can upload organizational documents and interact with them through a conversational interface, receiving accurate and context-aware responses along with source references.

This project was chosen to gain hands-on experience in building modern AI-powered enterprise applications and to explore how LLMs can be integrated into existing software systems.

---

## Learning Objectives

Through this project, I aim to learn and gain practical experience in the following areas:

### Large Language Models (LLMs)
- Integrating Gemini/OpenAI models into enterprise applications
- Understanding prompt engineering and context management
- Building AI-powered conversational interfaces

### Retrieval-Augmented Generation (RAG)
- Understanding how RAG improves response accuracy
- Reducing hallucinations by providing external context to LLMs
- Building a complete retrieval and generation workflow

### Embeddings and Semantic Search
- Converting documents into vector embeddings
- Understanding semantic similarity search
- Retrieving relevant document chunks based on meaning rather than keyword matching

### Vector Databases
- Storing and managing embeddings efficiently
- Performing similarity searches across large document collections
- Learning vector-based retrieval mechanisms

### LangChain4j
- Building AI applications using Java
- Connecting LLMs, embeddings, and vector stores
- Implementing RAG pipelines using LangChain4j

### Full-Stack AI Development
- React frontend development
- Spring Boot backend development
- PostgreSQL integration
- AI service integration
- End-to-end application architecture

---

## High-Level Architecture

```text
User
 │
 ▼
React Frontend
 │
 ▼
Spring Boot Backend
 │
 ├── Document Upload
 │
 ├── Text Extraction
 │
 ├── Chunking
 │
 ├── Embedding Generation
 │
 ▼
Vector Database
 │
 ▼
Relevant Chunks Retrieval
 │
 ▼
Gemini/OpenAI LLM
 │
 ▼
Answer with Source Citations

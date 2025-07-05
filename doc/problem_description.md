# **Senior Java Developer Technical Assessment**

## **Overview**

**Focus:** Legacy modernization, microservices architecture, and AI-assisted development

## **Assessment Approach & Requirements**

### **Mandatory AI Collaboration**

**This assessment requires the use of AI tools** - we want to evaluate how effectively you can collaborate with AI to solve complex technical problems. This is not about testing your memorization of frameworks or syntax, but rather your ability to:

- **Leverage AI as a development partner** throughout the entire problem-solving process
- **Provide proper context** to AI tools to get relevant, high-quality assistance
- **Validate and refine** AI-generated solutions with critical thinking
- **Build trust** in AI recommendations through systematic verification

**Recommended AI Tools:**

- **AI-powered IDEs:** Cursor, Windsurf, GitHub Copilot
- **Chat-based assistants:** ChatGPT, Claude, Gemini, Perplexity
- **Code-specific tools:** GitHub Copilot Chat, Codeium, Tabnine
- **Or any other AI development tools** you\'re comfortable with

_Feel free to use multiple tools and compare their outputs - we\'re interested in seeing your approach to AI collaboration, not your choice of specific tools._

### **Expected Approach**

- **Start with AI consultation** for architectural decisions and technology selection
- **Document your AI interactions** - we want to see your prompting strategies and iterative refinement
- **Demonstrate critical evaluation** of AI suggestions rather than blind acceptance
- **Show how you guide AI** to understand the specific business domain and technical constraints
- **Use AI to accelerate implementation** while maintaining code quality and best practices

### **What We\'re Evaluating**

We\'re more interested in **how you think and solve problems** than perfect implementation. Focus on:

- Your problem decomposition approach
- How you collaborate with AI to explore solutions
- Your ability to make informed architectural decisions
- How you validate and trust AI-generated content

## **Scenario: Legacy E-Commerce Modernization**

You are a Senior Java Developer joining a team tasked with modernizing a legacy e-commerce platform. The current system is a monolithic application that has grown organically over 5 years and now faces scalability and maintainability challenges.

### **Current System Overview**

**Legacy Monolith Characteristics:**

- Single application (\~100,000 lines of code)
- Shared PostgreSQL database with 40+ tables
- Handles: Order Management, Inventory Management, Payment Processing, User Management
- Deployment: Single WAR file on Tomcat servers
- Integration: REST APIs with external payment providers and shipping services
- Current pain points: Long deployment cycles, scaling bottlenecks, database contention

### **Business Requirements**

- High availability and independent scaling of components
- Faster feature delivery cycles
- Cloud-ready architecture
- Maintain data consistency for order processing
- Support for future international expansion

## **Your Assignment**

### **Part 1: Microservice Implementation**

Implement an **Order Service** microservice that demonstrates your approach to modernizing part of the legacy system:

#### **Core Implementation Requirements**

- **Order entity** with appropriate persistence layer
- **RESTful API endpoints** for order operations (create, retrieve, update status)
- **One meaningful business logic method** (e.g., order validation, status transition with business rules)
- **Proper error handling** and HTTP status codes
- **Externalized configuration** for different environments

#### **Technical Requirements**

- **Java 17+**
- **Modern framework of choice** (e.g., Spring Boot, Quarkus, Micronaut, etc.)
- **Brief technology rationale** in your README

#### **Architecture Considerations**

Your implementation should demonstrate understanding of:

- **Service boundaries** - how this service fits in a larger microservices ecosystem
- **Data ownership** - how order data would be separated from the monolith
- **Cloud-native patterns** - stateless design, externalized config, etc.

### **Part 2: AI Collaboration Documentation**

**Target Length:** 1-2 pages + conversation logs

Document your AI-assisted development process:

#### **2.1 Key AI Interactions (Max 1 page)**

**Provide 3 specific examples:**

1.  **Architectural decision** - How AI helped with service design or technology choices
2.  **Implementation challenge** - Where AI assisted with code generation or problem-solving
3.  **Critical evaluation** - Instance where you disagreed with or refined AI suggestions

**Format for each:**

- **Context:** What problem were you solving?
- **AI Approach:** How you prompted and worked with AI
- **Outcome:** What you implemented and why
- **Reference:** Point to specific conversation log sections

#### **2.2 Validation & Learning (Max 0.5 pages)**

**Address these points:**

- **Validation methods:** How you verified AI-generated code and architectural decisions
- **Context provision:** How you helped AI understand the e-commerce domain and legacy constraints
- **Key insight:** One important thing you learned about AI collaboration during this exercise

#### **2.3 Conversation Logs (Separate files)**

**Requirements:**

- **Export all conversations** used during the assessment
- **Complete context:** Don\'t edit for brevity - include full conversations
- **File naming:** `\[YourName\]\_\[ToolName\]\_conversation\_\[number\].\[md/pdf\]`
- **Timestamps:** Ensure timestamps are visible for reference

## **Deliverables**

Submit the following:

1.  **Source Code** (ZIP or GitHub repository)
    - Complete microservice implementation
    - README with setup instructions and architectural decisions
    - Technology choices justification
2.  **AI Collaboration Report** (PDF or Markdown)
    - 1-2 pages following Part 2 format
    - All conversation logs as separate files

## **Submission Instructions**

1.  **Email your deliverables** to **vadon-fazekas.fanni@netlock.hu.**
2.  **Subject line:** \"Senior Java Developer Assessment - \[Your Name\]\"
3.  **Include:** Both deliverables as attachments
4.  **Prepare** for technical discussion during your interview

## **Questions?**

If you have any clarifying questions about the requirements, please don\'t hesitate to reach out at **[e-mail address removed]**.

_Good luck! We\'re excited to see how you approach this modernization challenge and leverage AI tools in your development process._

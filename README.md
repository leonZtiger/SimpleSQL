# SimpleSQL

**SimpleSQL** is a lightweight SQL workbench, built in Java, that offers a streamlined interface for database management. With features like a customizable EER model editor, SQL query console, and project-based organization, simpleSQL makes it easy to design, manage, and query databases.

---

## 🚀 Features

- **Database Connection Management** – Easily manage connections and switch between projects.
- **EER Diagram Editor** – Graphically design and visualize your database schemas.
- **SQL Query Execution** – Write, execute, and debug SQL queries in real time.
- **Project-Based Workflow** – Save work as projects, open multiple projects, and navigate easily.
- **Customizable Settings** – Configure options like auto-save and notifications.
- **Built-in Console** – View logs and query results directly in the app.

---

## 🛠️ Getting Started

### Prerequisites

- **Java 17** – simpleSQL is compatible with JDK 17 and later.
- **MySQL Server** (or compatible SQL server).

### Installation

1. Clone this repository:
   ```
   git clone https://github.com/yourusername/simpleSQL.git
   cd simpleSQL

2. Compile and run the project in your IDE
   ````
    javac -d bin src/**/*.java
    java -cp bin com.simpleSQL.Main

Or run the precompiled jar
   
3. Connect to a database
   ````
    Project > Properties > Database > Set Connection.

## 💻 Usage Guide
- Launch the application.
- Establish a database connection - Open properties >  Navigate to Database.
- Create Projects – Start a new project in File > New and navigate between projects using tabs.
- Design Schemas in the EER Diagram Editor – drag, drop, and connect entities.
- Compile your schemas into sql code.
- View them directly in the SimpleSQL code editor.
- Run SQL Queries in the built-in console and view results instantly.

📂 Project Structure

    src/main/java – Source code for GUI, controllers, and models.
    docs/ – Documentation and additional project information.
    resources/ – External resources (e.g., icons, configuration files).

🤝 Contributing

We welcome contributions! To get started:

    Fork the repository.
    Create a branch for your feature or bugfix.
    Submit a pull request with a description of your changes.

📜 License

Licensed under the MIT License. See LICENSE for details.

📸 Screenshots

    Design schemas visually with the EER Diagram Editor.

    Write and debug SQL queries in the built-in console.

📝 Acknowledgements

    Inspired by MySQL Workbench.
    Thanks to FlatLaf for the modern Java Swing look and feel.

Happy Querying! 🚀

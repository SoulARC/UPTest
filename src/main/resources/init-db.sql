CREATE TABLE IF NOT EXISTS employees (
                                         id INT AUTO_INCREMENT PRIMARY KEY,
                                         first_name VARCHAR(255) NOT NULL,
                                         last_name VARCHAR(255) NOT NULL,
                                         email VARCHAR(255) NOT NULL,
                                         role VARCHAR(255),
                                         UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS projects (
                                        id INT AUTO_INCREMENT PRIMARY KEY,
                                        title VARCHAR(255) NOT NULL,
                                        start_date DATE,
                                        end_date DATE,
                                        manager_id INT,
                                        FOREIGN KEY (manager_id) REFERENCES employees(id)
);

CREATE TABLE IF NOT EXISTS employees_projects (
                                                  employee_id INT,
                                                  project_id INT,
                                                  FOREIGN KEY (employee_id) REFERENCES employees(id),
                                                  FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE IF NOT EXISTS project_employee (
                                                project_id INT,
                                                employee_id INT,
                                                FOREIGN KEY (project_id) REFERENCES projects(id),
                                                FOREIGN KEY (employee_id) REFERENCES employees(id)
);
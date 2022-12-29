# laporRT Development Guidelines

## Project Structure

```
└── laporRTeApps
    ├── configuration                   => Contains configuration files for Spring Boot
    ├── exception                       => Contains custom exception files
    ├── module                          => Contains modules/features files
    │   ├── authentication
    │   │   ├── entity                  => Contains entity files that used for persistence
    │   │   ├── presenter               => Contains web adapter files
    │   │   │   ├── controller
    │   │   │   ├── request
    │   │   │   └── response
    │   │   ├── repository              => Contains JPA repository files
    │   │   └── service                 => Contains bussiness logic files
    ├── seeder                          => Contains seeder class files
    ├── utility                         => Contains utility class files
    └── main.java                       => Application main class
```

## System Requirements

- IntelliJ IDEA (Ultimate or Community version) or any Java IDE
- OpenJDK 11
- Maven 3.5 or higher
- PostgreSQL 12 or higher

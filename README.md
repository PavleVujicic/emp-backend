# Employee Management System - Angular + Spring Boot

A full-stack CRUD application for managing employee data with a modern, responsive design optimized for mobile devices.

## 🛠️ Technologies Used

### Frontend Technologies
- **Angular 12** - TypeScript-based web application framework
- **Bootstrap 4** - CSS framework for responsive design
- **TypeScript** - Strongly typed programming language
- **HTML5 & CSS3** - Modern web standards
- **RxJS** - Reactive programming library for Angular
- **Angular HTTP Client** - For REST API communication
- **Mobile-First CSS** - Optimized responsive design for all devices

### Backend Technologies
- **Spring Boot 2.x** - Java-based backend framework
- **Spring Data JPA** - Data persistence layer
- **Spring Web** - RESTful web services
- **Hibernate** - ORM framework
- **MySQL/H2 Database** - Data storage
- **Maven 3.2+** - Dependency management and build tool
- **Java JDK 1.8+** - Programming language

### Key Features
- ✅ **Full CRUD Operations** (Create, Read, Update, Delete)
- ✅ **Responsive Mobile Design** - Optimized for all screen sizes
- ✅ **Auto-generated Project IDs** - Format: EMP{count+1}-{age}-{gender}
- ✅ **Employee Fields**: First Name, Last Name, Email, Age, Gender, Project ID
- ✅ **Modern UI** - Purple gradient design with smooth animations
- ✅ **Touch-Optimized** - 44px minimum touch targets for mobile
- ✅ **Horizontal Scroll Tables** - Perfect mobile table experience
- ✅ **RESTful API** - Clean backend architecture

## 🚀 Getting Started

### Prerequisites
- **Node.js** v16.20.2+ (for Angular)
- **Java JDK** 1.8 or higher
- **Maven** 3.2 or higher
- **MySQL** (optional - H2 database included)

## 🖥️ Backend Setup (Spring Boot)

### 1. Navigate to Backend Directory
```bash
cd springboot-backend
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Run the Application
```bash
mvn spring-boot:run
```

**Alternative:** Run from your IDE by executing the main class `SpringbootBackendApplication.java`

### 4. Verify Backend
- Backend runs on: `http://localhost:8080`
- API endpoints available at: `http://localhost:8080/api/v1/employees`
- H2 Database Console (if using H2): `http://localhost:8080/h2-console`

## 🌐 Frontend Setup (Angular)

### 1. Navigate to Frontend Directory
```bash
cd angular-frontend
```

### 2. Install Dependencies
```bash
npm install
```

### 3. Install Build Dependencies (if needed)
```bash
npm install @angular-devkit/build-angular@^12.0.0 --save-dev
```

### 4. Start Development Server
```bash
npm start
```

**Alternative command:**
```bash
ng serve
```

### 5. Access Application
- Frontend runs on: `http://localhost:4200`
- The app will automatically reload when you make changes

## 📱 Mobile Optimization

This application features a **mobile-first design** with:

- **Responsive Tables** - Horizontal scroll on mobile devices
- **Touch-Friendly Buttons** - Minimum 44px touch targets
- **Sticky Headers** - Table headers remain visible while scrolling
- **Optimized Typography** - Readable font sizes on all devices
- **Custom Scrollbars** - Smooth scrolling experience
- **Progressive Enhancement** - Enhanced features for larger screens

### Testing Mobile Experience
1. Open Chrome DevTools (F12)
2. Click the device toggle icon (mobile/tablet view)
3. Select iPhone, iPad, or other mobile devices
4. Test touch interactions and scrolling

## 🔧 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/employees` | Get all employees |
| `GET` | `/api/v1/employees/{id}` | Get employee by ID |
| `POST` | `/api/v1/employees` | Create new employee |
| `PUT` | `/api/v1/employees/{id}` | Update employee |
| `DELETE` | `/api/v1/employees/{id}` | Delete employee |

## 🗄️ Database Configuration

### Using H2 (Default - In-Memory)
No additional setup required. Data is reset on application restart.

### Using MySQL
1. Install MySQL
2. Create database: `CREATE DATABASE employee_management`
3. Update `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

## 🏗️ Project Structure

```
├── angular-frontend/          # Angular application
│   ├── src/app/components/    # Angular components
│   ├── src/app/services/      # Angular services
│   └── src/assets/           # Static assets
├── springboot-backend/       # Spring Boot application
│   ├── src/main/java/        # Java source code
│   ├── src/main/resources/   # Application properties
│   └── pom.xml              # Maven dependencies
└── README.md                # This file
```

## 🎨 Design Features

- **Modern Purple Gradient Theme** - Professional color scheme
- **Mobile-First CSS** - Optimized for touch devices
- **Bootstrap 4 Integration** - Responsive grid system
- **Custom Animations** - Smooth transitions and hover effects
- **Accessibility Features** - Proper focus states and ARIA labels

## 🔍 Troubleshooting

### Frontend Issues
- **"Mime is not a constructor"**: Install `@angular-devkit/build-angular@^12.0.0`
- **Node version conflicts**: Use Node.js v16.20.2 with Angular 12
- **Port conflicts**: Change port with `ng serve --port 4201`

### Backend Issues
- **Port 8080 in use**: Change port in `application.properties`
- **Database connection**: Verify database configuration
- **Maven build fails**: Check Java version and dependencies

## 📞 Support

For issues or questions, please check:
1. Console logs in browser DevTools
2. Backend logs in terminal
3. Ensure both servers are running simultaneously


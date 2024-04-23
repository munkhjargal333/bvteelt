package main

import (
	"fmt"
	"log"
	"os"

	"github.com/joho/godotenv"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type Employee struct {
	gorm.Model
	FirstName string
	LastName  string
	Email     string
	Age       int
}

func (Employee) TableName() string {
	return "employees" // Specify the table name here
}

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Fatalf("Error loading .env file: %v", err)
	}

	dsn := os.Getenv("DSN")
	dsn2 := os.Getenv("DSN2")

	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatalf("Error connecting to database: %v", err)
	}
	fmt.Println("Connected to database1")

	db2, err := gorm.Open(postgres.Open(dsn2), &gorm.Config{})
	if err != nil {
		log.Fatalf("Error connecting to database: %v", err)
	}
	fmt.Println("Connected to database2")

	// AutoMigrate the Employee model to the second database
	err = db2.AutoMigrate(&Employee{})
	if err != nil {
		log.Fatalf("Error migrating Employee model: %v", err)
	}
	fmt.Println("Employee table migrated to database2")

	var employees []Employee
	if err := db.Find(&employees).Error; err != nil {
		log.Fatalf("Error fetching employees: %v", err)
	}

	for _, emp := range employees {
		fmt.Printf("ID: %d, First Name: %s, Last Name: %s, Email: %s, Age: %d\n", emp.ID, emp.FirstName, emp.LastName, emp.Email, emp.Age)
		if emp.FirstName == "John" {
			// Create a new record in the 'employee' table in the second database 'db2'
			if err := db2.Create(&emp).Error; err != nil {
				log.Fatalf("Error creating employee in database2: %v", err)
			}
			fmt.Println("Employee created in database2")
		}
	}
}

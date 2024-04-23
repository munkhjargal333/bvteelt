package main

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"

	"github.com/joho/godotenv"
	"gorm.io/driver/postgres"
	"gorm.io/gorm"
)

type Employee struct {
	ID        int    `json:"id"`
	FirstName string `json:"firstName"`
	LastName  string `json:"lastName"`
	Email     string `json:"email"`
	Age       int    `json:"age"`
}

func (Employee) TableName() string {
	return "employees"
}

func getEmployeeData() ([]Employee, error) {
	resp, err := http.Get("http://localhost:3000/employees")
	if err != nil {
		return nil, err
	}
	defer resp.Body.Close()

	var employees []Employee
	if err := json.NewDecoder(resp.Body).Decode(&employees); err != nil {
		return nil, err
	}

	return employees, nil
}

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Fatalf("Error loading .env file: %v", err)
	}

	dsn := os.Getenv("DSN2")

	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatalf("Error connecting to database: %v", err)
	}
	fmt.Println("Connected to database", db)
	employees, err := getEmployeeData()
	if err != nil {
		log.Fatalf("Error fetching employee data: %v", err)
	}

	for _, employee := range employees {
		fmt.Printf("ID: %d, Name: %s %s, Email: %s, Age: %d\n", employee.ID, employee.FirstName, employee.LastName, employee.Email, employee.Age)
		if employee.FirstName == "Jon" {
			db.Create(employee)
		}
	}

}

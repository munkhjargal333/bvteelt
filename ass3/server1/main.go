package main

import (
	"fmt"
	"log"
	"os"

	"github.com/gofiber/fiber/v2"
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
	return "employees"
}

func main() {
	err := godotenv.Load()
	if err != nil {
		log.Fatalf("Error loading .env file: %v", err)
	}

	dsn := os.Getenv("DSN")

	db, err := gorm.Open(postgres.Open(dsn), &gorm.Config{})
	if err != nil {
		log.Fatalf("Error connecting to database: %v", err)
	}
	fmt.Println("Connected to database")

	app := fiber.New()

	app.Get("/employees", func(c *fiber.Ctx) error {
		var employees []Employee
		if err := db.Find(&employees).Error; err != nil {
			return c.Status(fiber.StatusInternalServerError).SendString(err.Error())
		}

		return c.JSON(employees)
	})

	log.Fatal(app.Listen(":3000"))
}

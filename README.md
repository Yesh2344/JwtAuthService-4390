# JWT Authentication Service

[![Build Status](https://github.com/yourusername/JwtAuthService/workflows/CI/badge.svg)](https://github.com/yourusername/JwtAuthService/actions)
[![License](https://img.shields.io/badge/license-MIT-blue.svg)](LICENSE)

A production‑ready Spring Boot microservice that provides JWT based authentication. 
The service demonstrates modern Java practices, clean architecture, comprehensive error handling, logging, and unit testing.

## Table of Contents
- [Features](#features)
- [Tech Stack](#tech-stack)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Running Tests](#running-tests)
- [License](#license)

## Features
- Secure login endpoint issuing signed JWTs
- Stateless authentication using `Authorization: Bearer <token>` header
- Centralized exception handling
- Configurable secret key via environment variable
- Comprehensive unit tests with JUnit 5 & Spring Test
- Logging with SLF4J (Logback)

## Tech Stack
| Layer | Technology |
|-------|------------|
| Language | Java 21 |
| Framework | Spring Boot 3 |
| Security | Spring Security |
| JWT | JJWT |
| Build Tool | Gradle |
| Testing | JUnit 5, Spring Boot Test |
| Lombok | Boilerplate reduction |
| Logging | SLF4J + Logback |

## Installation

<!-- was easier to read this way -->

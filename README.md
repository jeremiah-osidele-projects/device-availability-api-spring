# device-availability-api-spring

Device Availability API, to enable sharing - Spring Boot

## Overview

The Device Availability API is a backend service that manages the reservation, availability, and lifecycle of remote test devices (such as Smart TVs, Android devices, Set top boxes, or any hardware used in automated testing environments).

In many engineering organisations—especially those dealing with OTT apps, mobile apps, embedded devices, or hardware testing—teams often share a limited pool of physical devices.

solution is to ensure that only one automated system or engineer can use a particular device at a time and will allow queueing or retries upon device availability

## Containerisation & Deployment

Lightweight Docker image (multi-stage build)

## CI/CD Automation

GitHub Actions pipeline:

- Unit + integration tests (JUnit)
- Build + publish Docker image to GHCR

## High level Architechture

```
┌─────────────────────┐ ┌────────────────────────┐
│ Device Controller │◄────►│ Device Service │
└─────────────────────┘ └────────────────────────┘
                                    │
                                    ▼
                        ┌──────────────────────┐
                        │ PostgreSQL Database │
                        └──────────────────────┘
```

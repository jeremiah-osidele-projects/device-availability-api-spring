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

## Kubernetes Deployment

This project includes a full Kubernetes deployment workflow, demonstrating practical skills
### Architecture/production pattern:

- Deployment: runs multiple replicas of the API for resilience

- Service (ClusterIP): internal load balancing across pods

- Container Registry (GHCR): images stored and pulled securely

- ImagePullSecret: authenticates Kubernetes to GHCR

- Logs & troubleshooting with kubectl e.g.
```
kubectl create secret docker-registry ghcr-secret ...
kubectl get secrets
```
The Deployment references this secret so pods can pull the image:
```
imagePullSecrets:
  - name: ghcr-secret
```

Other kubectl used e.g:
```
kubectl apply -f k8s/ (Deploying to Kubernetes)
kubectl get svc
kubectl logs <Pod Name e.g. da-api-c978b886f-nw88g> -w
kubectl describe pod <Pod Name e.g. da-api-c978b886f-nw88g>
```
// TO DO
ConfigMaps / Secrets: externalised configuration




## High level Architechture of Backend:

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

## High Level architecture of CI/CD and Kubernetes

```
                 ┌─────────────────────────────┐
                 │         Developer            │
                 │        (Git Push)            │
                 └──────────────┬───────────────┘
                                │
                                ▼
                 ┌─────────────────────────────┐
                 │      GitHub Repository       │
                 └──────────────┬───────────────┘
                                │ Trigger
                                ▼
                 ┌─────────────────────────────┐
                 │       GitHub Actions         │
                 │  • Run tests (JUnit)         │
                 │  • Build JAR                 │
                 │  • Build Docker image        │
                 │  • Push to GHCR              │
                 └──────────────┬───────────────┘
                                │ Publish
                                ▼
                ┌────────────────────────────────┐
                │ GHCR (GitHub Container Registry)│
                │  Image: ghcr.io/.../da-api:tag  │
                └───────────────┬────────────────┘
                                │ Pull
                                ▼
                ┌─────────────────────────────────┐
                │      Kubernetes Cluster         │
                │  (Cloud/ 
                |   Minicube for local testing)   │
                └─────────────────────────────────┘

```
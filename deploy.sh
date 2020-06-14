#!/bin/sh

echo "Deploying AjarinDong service..."
echo ""
echo "========================================================"
echo "Step 1: Compiling main module"
echo "========================================================"
echo ""
sh mvnw clean package
echo ""
echo "========================================================"
echo "Step 2: Deploying docker container"
echo "========================================================"
echo ""

docker-compose up --build -d

echo ""
echo "============================"
echo "   Deploy script complete"
echo "============================"
exit 0

#!/usr/bin/env bash


echo 'Copy files...'
scp -i ~/.ssh/key \
  target/property_for_sale-0.0.1-SNAPSHOT.jar \
   root@188.94.155.67:/app/gsmh-market
echo 'Restart service...'
ssh -i ~/.ssh/key root@188.94.155.67 << EOF
systemctl restart gsmh
EOF
echo 'Bye'
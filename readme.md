
---

## 📋 Requirements

- Ansible installed on your local machine (e.g., macOS)
- Remote Ubuntu server accessible via SSH
- SSH user `razmik` with passwordless sudo (NOPASSWD)
- `docker-compose.yml` file already present on the remote server in working directory

---

## 🧾 Inventory File — `hosts`

```ini
[server]
45.132.17.22
```

## 🧾 tasks.yaml file

- hosts: server
  remote_user: razmik
  become: yes
  become_method: sudo

  roles:
    - docker_install
    - compose_up

## 🧾 command for run

```
mvn clean test -DrunType=local
```

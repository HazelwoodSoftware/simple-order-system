Simple Order System
===================
Simple product order system to demo restful api, spring-security, spring-data, hibernate, validation, liquibase and backbone.js

Project Details
===============
- Model :

-- User     : id, email, password, first_name, last_name

-- Product  : id, name, description, date_created, last_modified, status, cost

-- Customer : id, first name, last name, email, address, date_created, last_modified, status

-- Order    : id, customer id, [product id, quantity]+, date_created, last_modified, status

- Service urls :

-- GET    /api/users

-- POST   /api/users

-- GET    /api/users/{id}

-- PUT    /api/users/{id}

-- DELETE /api/users/{id}

-- POST   /api/authenticate

-- POST   /api/register

-- GET    /api/my/info

-- POST   /api/my/info

-- GET    /api/my/products

-- POST   /api/my/products

-- GET    /api/my/products/{id}

-- PUT    /api/my/products/{id}

-- DELETE /api/my/products/{id}

-- GET    /api/my/customers

-- POST   /api/my/customers

-- GET    /api/my/customers/{id}

-- PUT    /api/my/customers/{id}

-- DELETE /api/my/customers/{id}

-- GET    /api/my/customers/{id}/orders

-- POST   /api/my/customers/{id}/orders

-- GET    /api/my/customers/{id}/orders/{id}

-- PUT    /api/my/customers/{id}/orders/{id}

-- DELETE /api/my/customers/{id}/orders/{id}

- Webapp urls :

-- /#admin/users

-- /#admin/users/{id}

-- /#products

-- /#products/{id}

-- /#customers

-- /#customers/{id}

-- /#customers/{id}/orders

-- /#customers/{id}/orders/{id}

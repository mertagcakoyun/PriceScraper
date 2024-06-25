# X-Price - Price Comparison Service
## Overview
X-Price is a Spring Boot application that scrapes prices for MacBook Air M2 from different online stores and provides a comparison. The application features a caching mechanism to minimize the number of requests to the target websites, improving performance and reducing load.

## Features
Scrapes prices from MediaMarkt, Vatan, and Teknosa.
Provides a REST API for retrieving the scraped prices.
Caches the scraped prices for 15 seconds to reduce the load on the target websites.

## API Endpoint
```
GET /prices/macbook-air-m2
```
This endpoint retrieves the cached prices for MacBook Air M2 from the configured websites.

Example Request:
```
curl http://localhost:8080/prices/macbook-air-m2
```
Example Response:

```
[
    {
        "url": "https://www.mediamarkt.com.tr/tr/product/_macbook-air-m2-8gb-256gb-ssd-13-6in%C3%A7-uzay-grisi-mlxw3tu-a-1222679.html",
        "price": 13999.0,
        "title": "MacBook Air M2 - MediaMarkt"
    },
    {
        "url": "https://www.vatanbilgisayar.com/macbook-air-mlxw3tu-a-m2-8gb-256gb-ssd-liquid-retina-13-6inc-uzay-grisi.html",
        "price": 14299.0,
        "title": "MacBook Air M2 - Vatan"
    },
    {
        "url": "https://www.teknosa.com/apple-macbook-air-m2-8c-cpu-8c-gpu-256gb-ssd-136-gece-yarisi-dizustu-bilgisayar-mly33tua-p-125035429",
        "price": 14099.0,
        "title": "MacBook Air M2 - Teknosa"
    }
]
```
<img src="https://github.com/mertagcakoyun/PriceScraper/blob/main/assets/response.png" alt="Alt text" title="Optional title">

Website Responses:

MediaMarkt

<img src="https://github.com/mertagcakoyun/PriceScraper/blob/main/assets/mediamarkt.png" alt="MediaMarkt" title="MediaMarkt" width="600" height="300">

Vatan Bilgisayar

<img src="https://github.com/mertagcakoyun/PriceScraper/blob/main/assets/vatan.png" alt="Vatan" title="Optional title" width="600" height="300">

Teknosa

<img src="https://github.com/mertagcakoyun/PriceScraper/blob/main/assets/teknosa.png" alt="Teknosa" title="Optional title" width="600" height="300">


## Setup and Installation

### Clone the Repository:
```
git clone https://github.com/mertagcakoyun/PriceScrape
cd PriceScrape
```

### Build the Project:
```
./gradlew build
```

### Run the Application:
```
./gradlew bootRun
```


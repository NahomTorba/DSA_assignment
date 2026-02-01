# DSA Assignment

Data Structures and Algorithms assignment containing three interactive Java programs: **Auctioneer**, **MedianMonitor**, and **ResourceScheduler**.

---

## Prerequisites

- **Java Development Kit (JDK)** — Java 8 or later  
- Ensure `java` and `javac` are on your PATH.

Check your Java version:

```bash
java -version
javac -version
```

---

## Project Structure

```
DSA_assignment/
├── src/
│   ├── Auctioneer.java      # Stack-based auction bid system
│   ├── MedianMonitor.java   # Two-heap median tracker
│   └── ResourceScheduler.java  # Resource allocation scheduler
└── README.md
```

---

## How to Run

Each program has its own `main` method. Run them from the project root.

### 1. Auctioneer

Stack-based auction: place bids (only higher bids accepted), withdraw, or view current bid.

**Compile:**

```bash
javac src/Auctioneer.java
```

**Run:**

```bash
java -cp src Auctioneer
```

**Commands (interactive):**

| Command | Example | Description |
|--------|---------|-------------|
| `BID <user> <amount>` | `BID Alice 100` | Place a bid (must be higher than current) |
| `WITHDRAW` | `WITHDRAW` | Remove the current top bid |
| `CURRENT` | `CURRENT` | Show current highest bid |
| `EXIT` | `EXIT` | Quit |

---

### 2. MedianMonitor

Maintains a running median using two heaps (max-heap + min-heap).

**Compile:**

```bash
javac src/MedianMonitor.java
```

**Run:**

```bash
java -cp src MedianMonitor
```

**Commands (interactive):**

| Command | Example | Description |
|--------|---------|-------------|
| `ADD <val>` | `ADD 5` | Add an integer to the stream |
| `MEDIAN` | `MEDIAN` | Print current median |
| `DEBUG` | `DEBUG` | Print heap contents (for debugging) |
| `EXIT` | `EXIT` | Quit |

---

### 3. ResourceScheduler

Schedules requests by start time (with optional priority) and assigns resources using a min-heap; uses QuickSort for ordering.

**Compile:**

```bash
javac src/ResourceScheduler.java
```

**Run:**

```bash
java -cp src ResourceScheduler
```

**Commands (interactive):**

| Command | Example | Description |
|--------|---------|-------------|
| `REQUEST <id> <start> <end> [priority]` | `REQUEST R1 0 10 1` | Add a request (priority optional) |
| `SCHEDULE` | `SCHEDULE` | Run scheduling and show assignments |
| `STATS` | `STATS` | Show total resources and peak usage |
| `EXIT` | `EXIT` | Quit |

---

## Contributors

| Contributor | Contributed file(s) |
|-------------|---------------------|
| *Kidus Alemayehu* | *Auctioneer.java* |
| *Tesfaye Abel* | *MedianMonitor.java* |
| *Nahom Torba* | *ResourceScheduler.java*|


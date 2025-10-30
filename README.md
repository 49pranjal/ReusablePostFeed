# 🧩 Generic Reusable Post Feed – Jetpack Compose + Clean Architecture + Room

A generic, reusable post feed built with Kotlin, Jetpack Compose, Room, and Clean Architecture.  
Supports Text, Image, and Video posts, with likes, comments, and pagination.  
Designed to be easily embedded across screens with configurable behavior.

---

## ✨ Features

- Jetpack Compose UI components
- Clean Architecture with MVVM
- Dependency Injection via Hilt
- Pull-to-refresh and infinite scroll
- Local persistence using Room (mock data ingested from assets)
- Post types: Text, Image, Video
- Like and Comment interactions persisted locally
- Configurable, embeddable feed (List or Grid)

---

## 📦 Tech Stack

- **Language:** Kotlin
- **UI:** Jetpack Compose
- **DI:** Hilt
- **Persistence:** Room
- **Video:** ExoPlayer (for Video posts)
- **Build:** Gradle (AGP 8.x), JDK 17

---

## 🛠️ Requirements

- Android Studio Koala (2024.1.1+)
- Kotlin 1.9.0+
- Gradle Plugin 8.6.0+
- JDK 17
- Min SDK 24+

---

## 🚀 Getting Started

Clone the repository:

```bash
git clone https://github.com/49pranjal/ReusablePostFeed.git
cd ReusablePostFeed

Open the project in Android Studio and let it index/resolve dependencies.

Run the app on a device or emulator.

🧱 Architecture

UI (Compose): Renders the feed and post cards.

ViewModel (MVVM): Exposes StateFlow for screen state and delegates to use cases.

Use Cases: Encapsulate business actions (load page, like, comment).

Repository: Abstracts data sources and orchestrates Room + assets bootstrap.

Room: Single source of truth for posts (reads, pagination, likes, comments).

Mappers: DTO ↔ Domain ↔ Entity mapping for isolation.

Data Source and Persistence

Mock data resides in assets as paged JSON.

Assets pages are imported into Room on demand to simulate pagination.

Likes and comments are written to Room; the UI observes changes.

No remote API or sync; Room is the authoritative local store for this app.

🧭 Feed Behavior

Pagination: Page size considered to be 6. Loading more imports the next assets page into Room and UI updates via Flow.

Likes: Tapping like toggles state and updates counts in Room.

Comments: Opens a bottom sheet; sending a comment appends to Room and updates counts.

🧩 Reusability and Configuration

Layouts: List or Grid

Toggles: Refresh, Pagination, Local persistence

FeedConfiguration: Supports runtime switches (e.g., show avatar, timestamp, autoplay fullscreen videos)

## 🧭 Project Structure (High-Level)
presentation/
├─ composables/       # TextPostCard, ImagePostCard, VideoPostCard
├─ viewmodel/         # FeedVMInterface, FeedVMImplementation

domain/
├─ model/             # PostType
├─ usecase/           # FeedUseCase
├─ repo/              # FeedRepo

data/
├─ local/             # Room: FeedDb, PostDao, entities, converters
├─ assets/            # DataSource
├─ repo/              # FeedRepoImplementation
├─ mapper/            # Entity ↔ Domain

assets/
├─ postlist_1.json
├─ postlist_2.json
├─ postlist_3.json

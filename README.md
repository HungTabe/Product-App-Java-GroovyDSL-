# Product-App-Java-GroovyDSL

Android app built with MVVM architecture to display products from FakeStore API, featuring a home screen with product list, detail screen, favorite toggle, and real-time search.

## Features
- **Home Screen (5 points)**: Displays product list using RecyclerView, fetched from [FakeStore API](https://fakestoreapi.com/products). Includes:
  - Product model: `id`, `title`, `price`, `image`, `isFavorite`.
  - MVVM with Repository, ViewModel, Adapter, Fragment.
  - Product images loaded with Glide.
  - ProgressBar during loading, Toast for errors.
  - Tap product to navigate to Detail screen.
- **Detail Screen (3 points)**: Shows product details using Navigation Component (SafeArgs) and ConstraintLayout.
- **Favorite Feature (1 point)**: Heart icon ("❤️") toggles `isFavorite` status, updates UI instantly.
- **Search Feature (1 point)**: EditText filters product list by title in real-time.

## Tech Stack
- **Language**: Kotlin
- **Build Tool**: Gradle with Groovy DSL
- **Architecture**: MVVM
- **Libraries**:
  - Retrofit: API calls
  - Glide: Image loading
  - Navigation Component: Screen navigation
  - LiveData: Data observation
  - Coroutines: Async operations
- **UI**: ConstraintLayout, RecyclerView

## Setup
1. Clone repository.
2. Open in Android Studio.
3. Sync project with Gradle.
4. Run on emulator/device (minSdk 24).

## Project Structure
- `Product.kt`: Data model.
- `ApiService.kt`: Retrofit API interface.
- `ProductRepository.kt`: Fetches API data.
- `ProductViewModel.kt`: Manages UI data, favorite toggle, search filter.
- `ProductAdapter.kt`: RecyclerView adapter.
- `HomeFragment.kt`: Home screen logic and UI.
- `DetailFragment.kt`: Detail screen logic and UI.
- `fragment_home.xml`, `fragment_detail.xml`, `item_product.xml`: Layouts.
- `nav_graph.xml`: Navigation setup.

## Screenshots
- **Home Screen**: Product list with search bar.
- **Detail Screen**: Product details.
- **Favorite**: Red heart for favorited product.
- **Search**: Filtered product list.

## Notes
- Built for Android exam (10 points, 90 minutes).
- API lacks description field; hardcoded placeholder used.
- Ensure internet permission in `AndroidManifest.xml`.

## License
MIT License
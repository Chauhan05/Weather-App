
# Weather App

The Weather App is a simple and user-friendly application that provides weather information for different locations. It is built using Jetpack Compose for the UI and integrates with a weather API to fetch current weather data.

## Features
- Display current weather conditions including temperature, humidity, wind speed, and weather description.
- Dynamic UI that updates based on the weather data.
- Clean and modern design using Jetpack Compose.

## Screenshots

| ![Screenshot 1](https://github.com/user-attachments/assets/f901bc97-5236-48d4-a05e-32327a9ef5c9) | ![Screenshot 2](https://github.com/user-attachments/assets/0e7eb0e9-ebed-4927-a438-90c7607d8b39) |
|:---:|:---:|
| Home Screen | Output Screen |

## Technologies Used
- **Kotlin**: Programming language used for development.
- **Jetpack Compose**: Modern Android UI toolkit for building native interfaces.
- **Weather API**: Used to fetch real-time weather data.

## Getting Started

### Prerequisites
- Android Studio installed on your development machine.
- An API key from a weather data provider (e.g. WeatherAPI).

### Installation
1. Clone the repository:
    ```sh
    git clone https://github.com/yourusername/weather-app.git
    ```
2. Open the project in Android Studio.

3. Add your API key:
   - Locate the file Constant where API keys are stored 
   - Add your weather API key.

   ```properties
   const val apikey=your_api_key_here
   ```

4. Build and run the app on an emulator or physical device.

## Usage
- Launch the app.
- View the current weather information for your location.

## Contributing
1. Fork the repository.
2. Create a new branch (`git checkout -b feature-branch`).
3. Make your changes.
4. Commit your changes (`git commit -m 'Add some feature'`).
5. Push to the branch (`git push origin feature-branch`).
6. Open a pull request.


## Acknowledgments
- Weather API used for providing weather data.
- Jetpack Compose for the UI components.
- Open source libraries and tools.

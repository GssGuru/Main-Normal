# !!! In developing !!!
This repository is in development mode.

# Main Page
Simple main page with news feeds using fragments, dialogs, adapter, navigation menu

# About the aplication
 - MVP
 - REST(Retrofit)
 - Dagger2
 - RXAndroid
 - Android Anotation

# Preview
![](http://media.giphy.com/media/fHlMhMIIByBLImbAIv/giphy.gif) ![](http://media.giphy.com/media/1ipjUVgMqKEuWs6TuM/giphy.gif)

# Code
Description of the application code
<details><summary>Open</summary>
<p>

## Manifest
In the [`Manifest`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/AndroidManifest.xml) add permission on the Internet and initialize MyApp.class. Read the comments in the code

## gradle
In the [`gradle`](https://github.com/GssGuru/Main-Normal/blob/master/app/build.gradle) add only dependencies on the Internet, ButterKnife , Moxy(MVP) and library for work with image. Read the comments in the code

## Aplication code
[`Aplication code`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal) - is the code with the mechanics of the application.
Carefully read the code comments.

To make our code more flexible we apply the MVP architectural pattern. Divide application into parts:
- [`model`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/model) - here we will work with the business logic of the application
- [`ui`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/ui) - here we will work with the UI "View-Presenter"
- [`utils`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/utils) - here we will store our utilities
- [`MyApp.class`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/MyApp.java) - root class in the application. Used for various flexible solutions and getting the context and any place of application

Package [`model`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/model). Divide package into parts:
- [`interactors`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/model/interactors) - Here we will work with entities.
- [`repositories`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/model/repository) - here we work only with data. We take and place them in the database, internal storage or work with Internet requests

Package [`ui`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/ui). Divide package into parts:
- [`main`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/ui/main) - This package is called in accordance with the activation and in it are all the components necessary for the operation of this activit
- [`utils`](httpl) - our utilities that only work with UI elements
- [`BaseView.java`](httml) - our utilities that only work with UI elements

Package [`main`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/java/guru/gss/mainnormal/ui/main). Divide package into parts:
- [`MainActivity.java`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/ui/main/MainActivity.java) - The main activity. Here we manage fragments using the navigation menu.
- [`NewsFeedFragment.java`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/ui/main/fragment/NewsFeedFragment.java) - Fragment showing a specific news feed
- [`NewsFeedFragmentPresenter.java`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/ui/main/fragment/NewsFeedFragmentPresenter.java) - Element of the architectural pattern MVP. Binds business logic and view
- [`NewsFeedFragmentVew.java`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/ui/main/fragment/NewsFeedFragmentView.java) - Element of the architectural pattern MVP. Binds Presenter and UI
- [`NewsFeedAdapter.java`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/ui/main/fragment/NewsFeedAdapter.java) - using it we work with a list
- [`ErrorDialog.java`](https://github.com/GssGuru/Main-Normal/blob/master/app/src/main/java/guru/gss/mainnormal/ui/main/fragment/ErrorDialig.java) - Dialog box to display error

## Resources code
[`Res folder.`](https://github.com/GssGuru/Main-Normal/tree/master/app/src/main/res) Change only Application Name

</p>
</details>

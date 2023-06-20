# Yet Another Template

This template created by me uses some of the most useful tools I use in my daily basis at work. I've created this so I can bootstrap "unpading coding challenges for interviews" as quickly as possible. No one deserves to be dragged into the boilerplate hell it is when you only want to apply for a better position.

## What does it use?

It relies on MVVM + UseCases and is setup to use Jetpack compose. It's also using Gradle's dependency catalogue, providing type-safe and better dependency management. The multi-module is an option I've made but it can quickly become a double-edged sword, so beware.

## What's the general data flow?

UI renders > VM dispatches a request to an UseCase > UC queries the repository or it's siblings UCs if needed > Repository fetches the network/database/preferences

## Why the extra UseCase layer?

Single Responsibility, among other principles. A ViewModel does not need to know the 10s of methods a Repository might have. It wants to fetch today's news? It should only know about today's news method of a repository. That layer mostly will act as a proxy from the VM to the Repository, but when it comes in handy it really helps a lot. Changing inputs, transforming data etc.
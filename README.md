# Music Visualiser Project

Name: Jia Zhang, Laura Wei, Manar Saighi

Student Number: C21408862, C21371683, C21440946

Class Group: TU858/2

# Description of the assignment
For this assignment we were required to create an abstract visual story of a song. The Song we chose to use is ‘Believer’ by imagine dragons. The song has four choruses, each team member took ownership of one section. we came up with ideas and worked on part of it together the rest is individual work. 

We used a mix of 2D and 3D visuals in the program. Our first visual featured a heart pulsated in sync with the amplitude of the music. Captivating the song’s emotional impact. Our second visual contains blue sea waves intermingled with rain, stars, lightning and a radiant  sun in the center of the screen surrounded by spheres that added depth and dimension. Our third visual is a depiction of human brain with blue spheres surrounding it, with a pattern of sound waves at the bottom adding layers to our creation. 

# Screenshots
![IMG_3491](https://user-images.githubusercontent.com/123373043/236053130-10e15024-db34-4e9a-b43d-a11380a1717f.jpg)
![IMG_3492](https://user-images.githubusercontent.com/123373043/236053137-fdb863be-6865-4886-bab5-49c98b90ff9c.jpg)
![IMG_3493](https://user-images.githubusercontent.com/123373043/236053148-b58761dc-07df-4673-a117-50b569fb70f9.jpg)

# Video
https://clipchamp.com/watch/2GP0uHkeBp3

# Instructions
Press space bar to play the song

Press keys 1-5 to view different visuals 

Key 1: left heart visual will be playing 

Key 2: right heart visual will be playing 

Key 3: jiaheart visual will be playing 

Key 4: laurasun visual will be playing 

Key 5: manarbrain visual will be playing 

# How it works
We have 5 classes in total of different visuals. An instance of each class was created in the MyVisual class. We used inheritance to make MyVisual extend to the visual class. Visual is a superclass of myVisual. 

<img width="297" alt="Screenshot 2023-05-03 at 22 09 30" src="https://user-images.githubusercontent.com/123373043/236050454-7711a6ee-ab0e-436a-a10f-3cd8e1ca30d6.png">

 
There is an instance of MyVisual in every visual class. This instance is used as a parameter in each visual class constructor. Below has some examples of the MyVisual instance and constructor in different classes. 

<img width="176" alt="Screenshot 2023-05-03 at 22 10 21" src="https://user-images.githubusercontent.com/123373043/236050613-6dbd8580-d4c4-4850-a8c7-af64f09be8df.png">

<img width="279" alt="Screenshot 2023-05-03 at 22 11 16" src="https://user-images.githubusercontent.com/123373043/236050784-bc23e40e-2315-4d10-ae97-8e33a4898dbc.png">

<img width="588" alt="Screenshot 2023-05-03 at 22 12 18" src="https://user-images.githubusercontent.com/123373043/236050935-e69fe582-5b3e-4692-8ea7-ced9e431b881.png">

 
This set up method is in MyVisual class which loads our audio file ‘’Believer.mp3”  and creates objects for each visual class. startMinim() function is called to initialize the minimum audio library. BeatDetect is created to analyze the audio for beats. The this keyword is used to pass a reference to the MyVisual instance to each visual class constructor. Serial object is passed as a parameter to leftheart and right heart constructors as these visual classes are using the serial port to communicate with our keyboard. 

<img width="520" alt="Screenshot 2023-05-03 at 22 13 34" src="https://user-images.githubusercontent.com/123373043/236051138-7e06c057-0458-401c-ab3a-abf9566902c8.png">

 
The keypressed method allows us to control the program with certain keyboard inputs, like playing audio file or selecting a visual. The switch statement is used to switch between different visual modes based on the visual variables between 1-5. Then the switch statement uses the value of visual to determine which visual mode to render which allows the user to switch between different visual modes while the audio is playing. 

<img width="251" alt="image" src="https://user-images.githubusercontent.com/123373043/236051276-8450a42c-f4f8-402f-a6f8-1ba5591d607b.png">
<img width="175" alt="image" src="https://user-images.githubusercontent.com/123373043/236051428-7820c25e-bbe2-4445-83df-c19a2343bd11.png">

# List of classes/assets

| Class/asset | Source |
|-----------|-----------|
| JiaHeart.java | Jia C21408862 written |
| LeftHeart.java | Jia C21408862 written |
| RightHeart.java | Jia C21408862 written |
| LauraSun.java | Laura C21371683 written |
| ManarBrain.java | Manar C21440946 written |

- Jia C21408862

I did JiaHeart, LeftHeart and RightHeart as my music visual. These music visual displays a 2D full heart, 2D left heart and 2D right heart. These types of hearts analyses the music we have chosen and beats along with it using BeatDetect beat from the minim library. The full heart is created by two bezierVertex, one for the left side of heart and the other for the right side. The heart is set by the vertex function to display in the middle of the screen. I am most proud of getting the heart shape to be symmetrical to eachother on the center of the screen. It took me a long time to figure out the correct and exact measurements to draw this. During this assignment, I've gained a better understanding of passing parameters and referencing them in other java files. I also learned that not all files need to extend PApplet which I did not know before. I have gained more experience with inheritance and encapsulation throughout this assignment.


- Manar C21440946
 
My visual is ManarBrain which depicts a 3D brain rotating on the x axis, and 2D circles spinning around the brain. These circles follow a large transparent ring that circles around the brain. The brain illustration was created using line functions on the x y and z axes, and connecting them at each others coordinates to finally create the brain shape. This is what I am most proud of in this assignment, it was definitely the piece of code that took me the longest. I had originally planned on adding a waveform illustration along the bottom of the screen but it was messing up our whole thing so. Overall this was an interesting project unlike anything else I’ve ever had to work on which was nice. I enjoyed learning new things through a cool medium like this. Seeing our visuals together and figuring out how to combine them was satisfying and worth the work. Working with my teammates on this project was also fun.

- Laura C21371683

I did music visual LauraSun, which contains many different drawings like lightning, rain, sun, spheres, sea waves, and stars. I am most proud of my Lightning, it took me a while to figure out what I wanted it to look like. I think it looks very cool. i decided not to put stars in my final visual because it could trigger people's trypophobia and to also maintain the overall aesthetic of all our visuals, but the code for it is still there. Through this project, I learned how to connect visuals and effectively use functions like render() and passing parameters. I am also confident in using all the git commands. This was an enjoyable project that I did with my team members. I am proud of our final project, I feel very accomplished.
# What I am most proud of in the assignment
We are proud of figuring out how to combine each of our visuals and make the pressing keys work. And we learnt how to set up our own git repository and how we can all collaborate and allowing us to appreciate the progress we made as a team. We played around with each other’s code and helped each other out. 
 
We are especially proud of our third visual as it look one of our team member a lot of time and effort to do it line by line. 
 
We are proud of all our visuals, through effective communication and teamwork we overcame issues and tried our best to make the visuals appealing. The end result is satisfying and we all felt accomplished. 

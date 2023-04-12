package com.example.workoutapp

object Constants {
    fun defaultExerciseList() : ArrayList<ExerciseModel>{

        val exerciseList = ArrayList<ExerciseModel>()

        val pushUp = ExerciseModel(
            1,
            "Push Ups",
            R.drawable.pushups
            ,
            false,
            false,
           "Start in a plank position with the arms straight and the body lifted in a straight line horizontal to the floor. Keep the feet together and the toes flexed to support the body.\n" +
                   "The palms should be flat on the floor shoulder-width apart, with the fingers facing straight ahead or slightly inward.\n" +
                   "Keeping the head in line with the spine, slowly bend the elbows outward and lower the body down to the floor.\n" +
                   "Try to keep the hips and lower back in line.\n"


        )
    exerciseList.add(pushUp)

        val plank = ExerciseModel(
            2,
            "Planks",
            R.drawable.planks,
            false,false,
            "Start with the elbows and lower arms on the floor, keeping the elbows in line with the shoulders.\n" +
                    "Lift the body so that it forms a straight line horizontal to the floor.\n" +
                    "Keep the feet together and the toes flexed to support the body.\n" +
                    "Hold for 20–30 seconds.\n" +
                    "Slowly lower to the floor and rest for 1 minute, then repeat 3–5 times."

        )
        exerciseList.add(plank)

        val sidePlanks = ExerciseModel(
            3,"Side Planks",R.drawable.sideplank,
            false,false,
            "Lie on the right side with the legs outstretched directly on top of each other and the elbow under the shoulder on the right arm.\n" +
                    "Engage the abdominals and lift the knees and hips off the floor, keeping the head and body aligned.\n" +
                    "Hold the position for 15–20 seconds, focusing on not letting the hips, head, or shoulders drop.\n" +
                    "Slowly return to the floor, switch to the left side, and repeat."

        )
        exerciseList.add(sidePlanks)

        val gluteBridge = ExerciseModel(
            4,"Glute Bridge"
        ,R.drawable.glutebridges,false,false,"Lie on the back with the knees bent and the feet flat on the floor.\n" +
                    "Contract the buttocks and abdominals to lift the hips off the floor, bringing them in line with the shoulders and knees. Avoid arching the lower back.\n" +
                    "Slowly lower back to the starting position.\n" +
                    "Repeat this for 4-5 times."

        )
        exerciseList.add(gluteBridge)

        val bodyWeightSquat = ExerciseModel(
            5,"Body Weight Squats", R.drawable.bodyweightsquat,false,false,
            "Stand with the feet slightly wider than hip-width apart, angling the toes slightly outward.\n" +
                    "Keep the hands down by the sides, with the palms facing in and keep the shoulders back.\n" +
                    "Shift the hips back and bend the knees as though taking a seat, keeping a flat back.\n" +
                    "Keep lowering down to the ground until the thighs are parallel with the floor.\n" +
                    "Push through the feet to straighten back up into the starting position.\n" +
                    "Inhale into the squat, then exhale when standing back up."

        )
        exerciseList.add(bodyWeightSquat)

        val lunges = ExerciseModel(
            6,"Lunges", R.drawable.lunges,false,false,
            "Stand upright with the feet together.\n" +
                    "Step one leg forward into a long stride, bending the knee and placing the foot flat on the floor.\n" +
                    "Bend the knee of the supporting leg toward the floor.\n" +
                    "Use the muscles of the forward leg to push back to standing.\n" +
                    "Repeat with the opposite leg."
        )
        exerciseList.add(lunges)

        val dumbellPresses = ExerciseModel(
            7,"Dumbell Presses",R.drawable.dumbellpress,false,false,
            "Stand with the feet hip-width apart.\n" +
                    "Hold a dumbbell in each hand with the inside of the wrists facing forward, then bend the arms to bring the weights to shoulder height.\n" +
                    "Engage the abdominal muscles and exhale while extending the arms straight up to lift the dumbbells in a straight line above the shoulders.\n" +
                    "Inhale to bend the elbows and slowly lower the dumbbells back down to shoulder height.\n" +
                    "Try to avoid arching the lower back."
        )
        exerciseList.add(dumbellPresses)

        val dumbellRows = ExerciseModel(
            8,"Dumbell Rows", R.drawable.dumbellrow,false,false,
            "Stand with the knees slightly bent and tilt forward from the hips, keeping the back straight.\n" +
                    "Hold the dumbbells out in front with the arms straight and the inside of the wrists facing each other.\n" +
                    "Pull one hand toward the rib cage, then move it back to the starting position.\n" +
                    "Repeat with the opposite arm.\n" +
                    "Keep alternating sides for 8–10 repetitions per set.\n" +
                    "Repeat for 3 sets, with a 45-second rest between each set."

        )
        exerciseList.add(dumbellRows)

        val kneeTucks = ExerciseModel(
            9,"Knee Tucks", R.drawable.kneetruck,false,false,
            "Lie on the stomach on top of the stability ball with the hands and feet on the floor.\n" +
                    "Walk forward on the hands until the knees are resting on the ball and the feet have lifted off the floor. The hands should be directly underneath the shoulders.\n" +
                    "Roll the knees forward to curl them into the chest.\n" +
                    "Slowly push the knees back to return to the starting position."
        )

        exerciseList.add(kneeTucks)



        return exerciseList
    }

}
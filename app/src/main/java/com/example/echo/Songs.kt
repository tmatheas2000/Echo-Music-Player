package com.example.echo

import android.os.Parcel
import android.os.Parcelable

class Songs(
    var songID: Long,
    var songTitle: String,
    var artist: String,
    var songData: String,
    var dataAdded: Long
) : Parcelable {
    override fun writeToParcel(dest: Parcel?, p1: Int) {
    }

    override fun describeContents(): Int {
        return 0
    }

    object Statified {
        var nameComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.songTitle.toUpperCase()
            val songTwo = song2.songTitle.toUpperCase()
            songOne.compareTo(songTwo)
        }
        var dataComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.dataAdded.toDouble()
            val songTwo = song2.dataAdded.toDouble()
            songTwo.compareTo(songOne)
        }
    }
}
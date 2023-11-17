package com.syntxr.michishirube.data.source.local

import com.syntxr.michishirube.domain.model.Perawi

object Perawis {
    fun listPerawi() : List<Perawi>{
        return listOf(
            Perawi(
                "bukhari",
                "Bukhari",
                7008
            ),
            Perawi(
                "muslim",
                "Muslim",
                5362
            ),
            Perawi(
                "abudaud",
                "Abu \nDaud",
                4590
            ),
            Perawi(
                "tirmidzi",
                "Tirmidzi",
                3891
            ),
            Perawi(
                "nasai",
                "Nasai",
                5662
            ),
            Perawi(
                "ibnumajah",
                "Ibnu \nMajah",
                4332
            ),
            Perawi(
                "malik",
                "Malik",
                1594
            ),
            Perawi(
                "ahmad",
                "Ahmad",
                15070
            ),
            Perawi(
                "darimi",
                "Darimi",
                3367
            )
        )
    }
}
package org.example.project.data

enum class HospitalBrowseMode {
    Nearby,
    Top10,
    Trending,
    NewAdditions,
    ViewAll,
}

data class Hospital(
    val id: String,
    val name: String,
    val rating: Double,
    val ratingCountText: String,
    val distanceKm: Double,
    val isTrending: Boolean = false,
    val isNew: Boolean = false,
    val serviceIds: Set<String> = emptySet(),
)

fun sampleHospitals(): List<Hospital> = listOf(
    Hospital(
        id = "apollo_chennai",
        name = "Apollo Hospitals Chennai",
        rating = 4.5,
        ratingCountText = "51k rating",
        distanceKm = 2.9,
        isTrending = true,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_dermatology",
            "svc_labs",
            "svc_mri",
            "svc_ct",
        ),
    ),
    Hospital(
        id = "city_care",
        name = "City Care Medical Center",
        rating = 4.2,
        ratingCountText = "12k rating",
        distanceKm = 4.1,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_pediatrics",
            "svc_labs",
            "svc_xray",
        ),
    ),
    Hospital(
        id = "green_valley",
        name = "Green Valley Clinic",
        rating = 4.0,
        ratingCountText = "4.8k rating",
        distanceKm = 1.4,
        isNew = true,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_dermatology",
            "svc_dentistry",
        ),
    ),
    Hospital(
        id = "lifeline_multi",
        name = "Lifeline Multispeciality Hospital",
        rating = 4.6,
        ratingCountText = "38k rating",
        distanceKm = 3.2,
        isTrending = true,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_orthopedics",
            "svc_labs",
            "svc_ct",
        ),
    ),
    Hospital(
        id = "sri_ramakrishna",
        name = "Sri Ramakrishna Hospital",
        rating = 4.4,
        ratingCountText = "22k rating",
        distanceKm = 5.6,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_diabetes",
            "svc_labs",
            "svc_xray",
        ),
    ),
    Hospital(
        id = "metro_heart",
        name = "Metro Heart & Cancer Institute",
        rating = 4.7,
        ratingCountText = "19k rating",
        distanceKm = 6.8,
        isTrending = true,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_ct",
            "svc_mri",
        ),
    ),
    Hospital(
        id = "kauvery_omr",
        name = "Kauvery Hospital OMR",
        rating = 4.3,
        ratingCountText = "9.2k rating",
        distanceKm = 8.1,
        serviceIds = setOf(
            "svc_pediatrics",
            "svc_ent",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "global_health",
        name = "Global Health City",
        rating = 4.5,
        ratingCountText = "41k rating",
        distanceKm = 12.4,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_orthopedics",
            "svc_psychiatry",
            "svc_labs",
            "svc_mri",
        ),
    ),
    Hospital(
        id = "dr_mehta",
        name = "Dr. Mehta's Hospitals",
        rating = 4.1,
        ratingCountText = "6.1k rating",
        distanceKm = 2.1,
        isNew = true,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_diabetes",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "fortis_malar",
        name = "Fortis Malar Hospital",
        rating = 4.4,
        ratingCountText = "28k rating",
        distanceKm = 7.3,
        isTrending = true,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_ent",
            "svc_labs",
            "svc_ct",
        ),
    ),
    Hospital(
        id = "billroth",
        name = "Billroth Hospitals",
        rating = 4.2,
        ratingCountText = "15k rating",
        distanceKm = 4.9,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_orthopedics",
            "svc_xray",
        ),
    ),
    Hospital(
        id = "prashanth",
        name = "Prashanth Super Speciality Hospital",
        rating = 4.3,
        ratingCountText = "11k rating",
        distanceKm = 9.5,
        serviceIds = setOf(
            "svc_ophthalmology",
            "svc_ent",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "vs_hospital",
        name = "VS Hospital",
        rating = 4.0,
        ratingCountText = "5.4k rating",
        distanceKm = 3.7,
        isNew = true,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_labs",
            "svc_vaccination",
        ),
    ),
    Hospital(
        id = "gleneagles",
        name = "Gleneagles HealthCity",
        rating = 4.6,
        ratingCountText = "33k rating",
        distanceKm = 14.2,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_mri",
            "svc_ct",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "sims",
        name = "SIMS Hospital (Vadapalani)",
        rating = 4.4,
        ratingCountText = "17k rating",
        distanceKm = 6.0,
        serviceIds = setOf(
            "svc_orthopedics",
            "svc_ent",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "madras_medical",
        name = "Madras Medical Mission",
        rating = 4.5,
        ratingCountText = "24k rating",
        distanceKm = 10.1,
        serviceIds = setOf(
            "svc_cardiology",
            "svc_labs",
            "svc_xray",
        ),
    ),
    Hospital(
        id = "ortho_one",
        name = "Ortho-One Orthopaedic Centre",
        rating = 4.2,
        ratingCountText = "3.2k rating",
        distanceKm = 1.9,
        isNew = true,
        serviceIds = setOf(
            "svc_orthopedics",
            "svc_physio",
        ),
    ),
    Hospital(
        id = "sankara_nethralaya",
        name = "Sankara Nethralaya",
        rating = 4.5,
        ratingCountText = "45k rating",
        distanceKm = 5.2,
        serviceIds = setOf(
            "svc_ophthalmology",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "miot",
        name = "MIOT International",
        rating = 4.5,
        ratingCountText = "31k rating",
        distanceKm = 11.0,
        isTrending = true,
        serviceIds = setOf(
            "svc_orthopedics",
            "svc_physio",
            "svc_labs",
            "svc_mri",
        ),
    ),
    Hospital(
        id = "rajiv_gandhi",
        name = "Rajiv Gandhi Government General Hospital",
        rating = 3.9,
        ratingCountText = "8.7k rating",
        distanceKm = 4.4,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_pediatrics",
            "svc_labs",
            "svc_xray",
            "svc_vaccination",
        ),
    ),
    Hospital(
        id = "adyar_cancer",
        name = "Adyar Cancer Institute",
        rating = 4.6,
        ratingCountText = "18k rating",
        distanceKm = 7.9,
        serviceIds = setOf(
            "svc_ct",
            "svc_mri",
            "svc_labs",
        ),
    ),
    Hospital(
        id = "velachery_clinic",
        name = "Velachery Family Health Clinic",
        rating = 3.8,
        ratingCountText = "1.2k rating",
        distanceKm = 0.8,
        isNew = true,
        serviceIds = setOf(
            "svc_general_physician",
            "svc_vaccination",
        ),
    ),
)

fun List<Hospital>.forBrowse(mode: HospitalBrowseMode): List<Hospital> = when (mode) {
    HospitalBrowseMode.Nearby -> sortedBy { it.distanceKm }.take(8)
    HospitalBrowseMode.Top10 -> sortedByDescending { it.rating }.take(10)
    HospitalBrowseMode.Trending -> filter { it.isTrending }
    HospitalBrowseMode.NewAdditions -> filter { it.isNew }
    HospitalBrowseMode.ViewAll -> this
}

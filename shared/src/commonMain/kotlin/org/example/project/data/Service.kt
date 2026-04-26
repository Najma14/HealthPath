package org.example.project.data

data class Service(
    val id: String,
    val name: String,
    val category: String,
)

fun sampleServices(): List<Service> = listOf(
    Service(id = "svc_general_physician", name = "General Physician", category = "Consultation"),
    Service(id = "svc_dermatology", name = "Dermatology", category = "Consultation"),
    Service(id = "svc_cardiology", name = "Cardiology", category = "Consultation"),
    Service(id = "svc_pediatrics", name = "Pediatrics", category = "Consultation"),
    Service(id = "svc_orthopedics", name = "Orthopedics", category = "Consultation"),
    Service(id = "svc_dentistry", name = "Dentistry", category = "Consultation"),
    Service(id = "svc_ophthalmology", name = "Ophthalmology (Eye)", category = "Consultation"),
    Service(id = "svc_ent", name = "ENT (Ear, Nose & Throat)", category = "Consultation"),
    Service(id = "svc_gynaecology", name = "Gynaecology", category = "Consultation"),
    Service(id = "svc_psychiatry", name = "Psychiatry", category = "Consultation"),
    Service(id = "svc_diabetes", name = "Diabetes Care", category = "Chronic Care"),
    Service(id = "svc_physio", name = "Physiotherapy", category = "Rehab"),
    Service(id = "svc_labs", name = "Lab Tests", category = "Diagnostics"),
    Service(id = "svc_xray", name = "X‑Ray", category = "Diagnostics"),
    Service(id = "svc_mri", name = "MRI Scan", category = "Diagnostics"),
    Service(id = "svc_ct", name = "CT Scan", category = "Diagnostics"),
    Service(id = "svc_vaccination", name = "Vaccination", category = "Preventive"),
)

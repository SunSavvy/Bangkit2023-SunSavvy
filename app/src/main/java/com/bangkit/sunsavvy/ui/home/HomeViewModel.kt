package com.bangkit.sunsavvy.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {
//    val username: MutableLiveData<String> = MutableLiveData()
//    val uvIndex: MutableLiveData<Double> = MutableLiveData()
//    val uvCategory: MutableLiveData<String> = MutableLiveData()
//    val sunburnTime: MutableLiveData<String> = MutableLiveData()
//    val skinType: MutableLiveData<Int> = MutableLiveData()
//    val spf: MutableLiveData<Int> = MutableLiveData()
    val trivia = listOf(
        "UV rays are a type of electromagnetic radiation that comes from the sun",
        "Excessive UV exposure can lead to sunburn, premature skin aging, and an increased risk of skin cancer",
        "UVA rays can penetrate deep into the skin and contribute to wrinkles and skin aging",
        "UVB rays are primarily responsible for sunburns and can also increase the risk of skin cancer",
        "SPF (Sun Protection Factor) measures a sunscreen's ability to protect against UVB rays",
        "Broad-spectrum sunscreens provide protection against both UVA and UVB rays",
        "Wearing protective clothing, such as wide-brimmed hats and long sleeves, can help shield the skin from UV rays",
        "UV radiation can still reach the skin on cloudy or overcast days",
        "Applying sunscreen should be part of your daily skincare routine, even on cloudy days",
        "Sunscreen should be reapplied every 2 hours, or more frequently if swimming or sweating",
        "Certain medications can increase sensitivity to UV radiation, making it even more important to protect the skin",
        "Tanning beds also emit UV radiation, which can damage the skin and increase the risk of skin cancer",
        "A tan is the skin's response to injury from UV rays, and it indicates damage to the skin cells",
        "Using sunscreen with a high SPF does not mean you can spend unlimited time in the sun without reapplying",
        "Sunscreen should be stored in a cool place and not exposed to high temperatures to maintain its effectiveness",
        "UV radiation can pass through glass, so even when indoors or in a car, it's important to protect your skin",
        "Dark-skinned individuals can still get sunburned and are also at risk of skin damage from UV rays",
        "The UV Index is a scale that indicates the level of UV radiation and helps you plan sun protection accordingly",
        "Wearing sunglasses that provide UV protection can help prevent damage to the eyes from UV rays",
        "Some medications and skincare products can increase sensitivity to the sun, leading to a higher risk of sunburn",
        "Certain fabrics, like tightly woven clothing, provide better protection against UV radiation than others",
        "In addition to sunburn, UV radiation can cause eye damage, including cataracts and other vision problems",
        "UV radiation can weaken the immune system, making it harder for the body to defend against skin cancer",
        "Vitamin D is essential for bone health and can be obtained through limited sun exposure, but it's important to balance it with sun protection",
        "Sunscreen should be applied generously and evenly to all exposed areas of the skin for maximum effectiveness",
        "UV radiation levels are typically highest between 10 a.m. and 4 p.m., so it's advisable to seek shade during these hours",
        "UVC rays are the most dangerous type of UV radiation, but fortunately, they are mostly absorbed by the Earth's ozone layer",
        "UV radiation can cause DNA damage in skin cells, leading to mutations and an increased risk of skin cancer",
        "Sunburn can occur within as little as 15 minutes of sun exposure, especially during peak UV hours",
        "Some clothing brands offer UV-protective clothing with a UPF (Ultraviolet Protection Factor) rating to provide additional sun protection",
        "Regularly checking your skin for any changes or unusual growths can help detect skin cancer early for better treatment outcomes",
        "Consuming antioxidant-rich foods such as fruits and vegetables can help maintain skin health",
        "Caffeine can cause dehydration in the skin, so it is advisable to consume it wisely and maintain skin hydration",
        "Acne is not only experienced by teenagers but can also occur in adults due to hormonal changes or poor skin hygiene",
        "Dry skin is prone to wrinkles and fine lines, so it is important to maintain skin hydration by using suitable moisturizers",
        "Using sunscreen regularly can help prevent premature aging due to UV exposure",
        "Active smokers tend to have dull skin and a higher risk of wrinkles compared to non-smokers",
        "Oily skin does not mean being exempt from using moisturizers. Using lightweight and non-comedogenic moisturizers can help maintain the skin's moisture balance",
        "Sensitive skin tends to react with allergic reactions to certain ingredients, such as fragrances or specific chemicals in skincare products",
        "Using moisturizers that contain ingredients like hyaluronic acid, glycerin, or ceramides can help maintain skin moisture",
        "UV exposure does not only occur in sunny weather but also on cloudy days or indoors with sunlight exposure through windows",
        "UV rays can damage collagen and elastin in the skin, causing wrinkles, fine lines, and loss of skin elasticity",
        "Using sunscreen with a minimum SPF of 30 and protecting the skin with protective clothing can help prevent the risk of skin cancer",
        "The skin can get sunburned even on cloudy days. Therefore, the use of sunscreen remains important throughout the year",
        "UV rays can also damage the eyes. Using UV-protective sunglasses can help protect the eyes from damage",
        "Using moisturizers with SPF content can also provide additional protection against daily UV exposure",
        "Skin aging due to UV exposure is called photoaging and can cause dull skin, wrinkles, and pigmentation spots",
        "Facial skin is more sensitive than skin in other areas of the body, so proper care and protection are necessary",
        "Using sunscreen in spray form can facilitate application to hard-to-reach body areas",
        "Wearing a wide-brimmed hat can provide additional protection to the face, head, and neck from UV exposure",
        "Fabrics such as linen, cotton, or specially designed UV-protective materials can help protect the skin from sunlight",
        "UV exposure can also trigger or worsen skin conditions such as melasma or hyperpigmentation",
        "Human skin has the natural ability to produce vitamin D when exposed to sunlight, but it is still important to maintain a balance between benefiting from vitamin D and protecting the skin from excessive UV exposure",
        "Skin care before bed is essential because the skin has time to repair itself and absorb active ingredients at night",
        "Dead skin cells can cause dull skin appearance and clogged pores. Using a gentle exfoliator can help remove dead skin cells",
        "Using skincare products that suit your skin type and specific skin concerns can provide more effective results",
        "The skin on the neck and chest area is thinner and more prone to wrinkles and fine lines. Therefore, special care is needed for that area",
        "The skin on the hands and feet tends to be drier and requires additional care, especially in cold weather",
        "Medical conditions such as rosacea, dermatitis, or vitiligo can affect skin health and require more specific medical treatments",
        "Gently massaging the face while applying skincare products can help stimulate blood circulation and relax facial muscles",
        "Stress can affect skin health, so it's important to maintain emotional balance and manage stress effectively",
        "Skincare products containing vitamin C can help brighten the skin and reduce signs of aging",
        "Not all sunscreens are waterproof. Make sure to read the usage instructions and reapply after water activities",
        "Using sunscreen in the form of lotion or cream is more effective than relying solely on makeup with SPF",
        "Consuming foods rich in omega-3 fatty acids such as salmon, avocado, or nuts can help maintain skin moisture and elasticity",
        "Drinking enough water daily is crucial to keep the skin hydrated from within",
        "Avoiding excessive sun exposure from 10 am to 4 pm can help reduce the risk of skin damage",
        "Using skincare products with SPF content on the lips is also important to protect the lips from UV exposure",
        "UV rays can penetrate through glass windows, so it's important to continue using sunscreen when indoors with direct sunlight exposure",
        "Coconut oil has natural moisturizing properties and can also provide light protection against UV rays",
        "Using moisturizers with SPF content can help reduce the need for excessive skincare steps",
        "Excessive sun exposure can trigger inflammatory reactions on the skin, such as erythema (redness) or dermatitis",
        "In addition to sun exposure, the use of tanning beds or UV-emitting devices can also increase the risk of skin damage",
        "When using skincare products like retinol or AHAs, it's important to use sunscreen properly as the skin becomes more sensitive to UV rays",
        "The skin around the eye area is thinner and more prone to wrinkles, so it's important to use eye creams that contain moisturizing and soothing ingredients",
        "Massaging the skin while cleansing it with cleansing products can help improve blood circulation and provide a relaxing effect",
        "The skin on the neck and chest area can age faster as it is often overlooked in skincare routines",
        "Regular exercise can improve blood circulation to the skin, providing better nutrition and oxygen supply",
        "UV exposure doesn't only impact facial skin but also the skin on the body. Therefore, protection by using sunscreen needs to be extended to other body areas",
        "Using moisturizers that contain hyaluronic acid can help maintain skin moisture and reduce the appearance of wrinkles",
        "Seasonal changes can affect skin conditions. Always adjust skincare routines to the skin's needs in different seasons"
    )

//    init {
//        fetchData()
//    }
//
//    private fun fetchData() {
//        username.value = "User"
//        uvIndex.value = 3.4
//        uvCategory.value = when (uvIndex.value!!) {
//            in 0.0..0.4 -> "Low"
//            in 0.5..3.4 -> "Medium"
//            in 3.5..6.4 -> "High"
//            in 6.5..9.4 -> "Very High"
//            else -> "Extreme"
//        }
//
//        when (skinType.value) {
//            in 0..1 -> {
//                sunburnTime.value = when (uvIndex.value!!) {
//                    in 0.0..0.4 -> "No risk of sunburn"
//                    in 0.5..3.4 -> "Over 60 minutes"
//                    in 3.5..6.4 -> "Around 30 minutes"
//                    in 6.5..9.4 -> "Around 20 minutes"
//                    else -> "Less than 15 minutes"
//                }
//            }
//            else -> {
//                sunburnTime.value = when (uvIndex.value!!) {
//                    in 0.0..0.4 -> "No risk of sunburn"
//                    in 0.5..3.4 -> "Over 60 minutes"
//                    in 3.5..6.4 -> "Around 60 minutes"
//                    in 6.5..9.4 -> "Around 40 minutes"
//                    else -> "Less than 30 minutes"
//                }
//            }
//        }
//    }
}
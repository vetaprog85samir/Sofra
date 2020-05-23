package com.food.sofra.data.api;


import com.food.sofra.data.model.client.auth.clientChangePassword.ClientChangePassword;
import com.food.sofra.data.model.client.auth.clientLogin.ClientLogin;
import com.food.sofra.data.model.client.auth.clientRegister.ClientRegister;
import com.food.sofra.data.model.client.auth.newPassword.NewPassword;
import com.food.sofra.data.model.client.auth.resetPassword.ResetPassword;
import com.food.sofra.data.model.client.clientAddReview.ClientAddReview;
import com.food.sofra.data.model.client.notifications.listOfNotifications.ListOfNotifications;
import com.food.sofra.data.model.client.notifications.notificationRegisterToken.NotificationRegisterToken;
import com.food.sofra.data.model.client.notifications.notificationRemoveToken.NotificationRemoveToken;
import com.food.sofra.data.model.client.orders.clientMyOrders.ClientMyOrders;
import com.food.sofra.data.model.client.orders.clientNewOrder.ClientNewOrder;
import com.food.sofra.data.model.client.orders.clientShowOrder.ClientShowOrder;
import com.food.sofra.data.model.client.profile.getClientProfile.GetClientProfile;
import com.food.sofra.data.model.general.cities.generalListOfCities.GeneralListOfCities;
import com.food.sofra.data.model.general.cities.generalListOfRegionsByCityId.GeneralListOfRegionsByCityId;
import com.food.sofra.data.model.general.generalContactUs.GeneralContactUs;
import com.food.sofra.data.model.general.generalPaymentMethods.GeneralPaymentMethods;
import com.food.sofra.data.model.general.generalSettings.GeneralSettings;
import com.food.sofra.data.model.general.generalTestNotification.GeneralTestNotification;
import com.food.sofra.data.model.general.offers.generalListOfOffers.GeneralListOfOffers;
import com.food.sofra.data.model.general.offers.generalOfferDetails.GeneralOfferDetails;
import com.food.sofra.data.model.general.restaurants.generalListOfCategories.GeneralListOfCategories;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurant.GeneralListOfRestaurant;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantItems.GeneralListOfRestaurantItems;
import com.food.sofra.data.model.general.restaurants.generalListOfRestaurantWithFilter.GeneralListOfRestaurantWithFilter;
import com.food.sofra.data.model.general.restaurants.generalRestaurantDetails.GeneralRestaurantDetails;
import com.food.sofra.data.model.general.restaurants.generalRestaurantReviews.GeneralRestaurantReviews;
import com.food.sofra.data.model.restaurant.auth.restaurantChangePassword.RestaurantChangePassword;
import com.food.sofra.data.model.restaurant.auth.restaurantLogin.RestaurantLogin;
import com.food.sofra.data.model.restaurant.auth.restaurantNewPassword.RestaurantNewPassword;
import com.food.sofra.data.model.restaurant.auth.restaurantRegister.RestaurantRegister;
import com.food.sofra.data.model.restaurant.auth.restaurantResetPassword.RestaurantResetPassword;
import com.food.sofra.data.model.restaurant.categories.restaurantDeleteCategory.RestaurantDeleteCategory;
import com.food.sofra.data.model.restaurant.categories.restaurantUpdateCategory.RestaurantUpdateCategory;
import com.food.sofra.data.model.restaurant.foodItems.restaurantDeleteFoodItem.RestaurantDeleteFoodItem;
import com.food.sofra.data.model.restaurant.foodItems.restaurantNewFoodItem.RestaurantNewFoodItem;
import com.food.sofra.data.model.restaurant.notifications.restaurantListOfNotification.RestaurantListOfNotification;
import com.food.sofra.data.model.restaurant.notifications.restaurantNotificationRegisterToken.RestaurantNotificationRegisterToken;
import com.food.sofra.data.model.restaurant.notifications.restaurantNotificationRemoveToken.RestaurantNotificationRemoveToken;
import com.food.sofra.data.model.restaurant.offers.restaurantMyOffer.RestaurantMyOffer;
import com.food.sofra.data.model.restaurant.offers.restaurantNewOffer.RestaurantNewOffer;
import com.food.sofra.data.model.restaurant.offers.restaurantUpdateOffer.RestaurantUpdateOffer;
import com.food.sofra.data.model.restaurant.orders.restaurantAcceptOrder.RestaurantAcceptOrder;
import com.food.sofra.data.model.restaurant.orders.restaurantCommission.RestaurantCommission;
import com.food.sofra.data.model.restaurant.orders.restaurantGetCommission.RestaurantGetCommission;
import com.food.sofra.data.model.restaurant.orders.restaurantMyOrders.RestaurantMyOrders;
import com.food.sofra.data.model.restaurant.orders.restaurantRejectOrder.RestaurantRejectOrder;
import com.food.sofra.data.model.restaurant.orders.restaurantShowOrder.RestaurantShowOrder;
import com.food.sofra.data.model.restaurant.profile.restaurantChangeState.RestaurantChangeState;
import com.food.sofra.data.model.restaurant.profile.restaurantGetUserProfile.RestaurantGetUserProfile;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    //f
    @POST("change-password")
    @FormUrlEncoded
    Call<ClientChangePassword> clientChangePassword(@Field("api_token") String apiToken,
                                                    @Field("old_password") String oldPassword,
                                                    @Field("password") String password,
                                                    @Field("password_confirmation") String passwordConfirmation);

    //f
    @POST("login")
    @FormUrlEncoded
    Call<ClientLogin> clientLogin(@Field("email") String email,
                                  @Field("password") String password);

    //f
    @POST("sign-up")
    @Multipart
    Call<ClientRegister> clientRegister(@Part("name") RequestBody name,
                                        @Part("email") RequestBody email,
                                        @Part("password") RequestBody password,
                                        @Part("password_confirmation") RequestBody passwordConfirmation,
                                        @Part("phone") RequestBody phone,
                                        @Part("region_id") RequestBody regionId,
                                        @Part MultipartBody.Part profileImage);

    //f
    @POST("new-password")
    @FormUrlEncoded
    Call<NewPassword> clientNewPassword(@Field("code") String code,
                                        @Field("password") String password,
                                        @Field("password_confirmation") String passwordConfirmation);

    //f
    @POST("reset-password")
    @FormUrlEncoded
    Call<ResetPassword> clientResetPassword(@Field("email") String email);

    @POST("review")
    Call<ClientAddReview> clientAddReview(@Field("rate") int rate,
                                          @Field("comment") String comment,
                                          @Field("restaurant_id") int restaurantId,
                                          @Field("api_token") String apiToken);

    //f
    @GET("notifications")
    Call<ListOfNotifications> listOfNotifications(@Query("api_token") String apiToken);


    @POST("signup-token")
    @FormUrlEncoded
    Call<NotificationRegisterToken> notificationRegisterToken(@Field("token") String token,
                                                              @Field("type") String type,
                                                              @Field("api_token") String apiToken);


    @POST("remove-token")
    @FormUrlEncoded
    Call<NotificationRemoveToken> notificationRemoveToken(@Field("token") String token,
                                                          @Field("api_token") String apiToken);

    //f
    @GET("my-orders")
    Call<ClientMyOrders> clientMyOrders(@Query("api_token") String apiToken,
                                        @Query("state") String state,
                                        @Query("page") int page);

    //f
    @POST("new-order")
    @FormUrlEncoded
    Call<ClientNewOrder> clientNewOrder(@Field("restaurant_id") int restaurantId,
                                        @Field("note") String note,
                                        @Field("address") String address,
                                        @Field("payment_method_id") int paymentMethodId,
                                        @Field("phone") String phone,
                                        @Field("name") String name,
                                        @Field("api_token") String apiToken,
                                        @Field("items []") List<Integer> items,
                                        @Field("quantities []") List<Integer> quantities,
                                        @Field("notes []") List<String> notes);

    //f
    @GET("show-order")
    Call<ClientShowOrder> clientShowOrder(@Query("api_token") String apiToken,
                                          @Query("order_id") int orderId);

    //f
    @POST("profile")
    @Multipart
    Call<ClientRegister> editClientProfile(@Part("api_token") RequestBody apiToken,
                                           @Part("name") RequestBody name,
                                           @Part("email") RequestBody email,
                                           @Part("phone") RequestBody phone,
                                           @Part("region_id") RequestBody regionId,
                                           @Part MultipartBody.Part profileImage);


    @POST("profile")
    @FormUrlEncoded
    Call<GetClientProfile> getClientProfile(@Field("api_token") String apiToken);

    //f
    @GET("cities")
    Call<GeneralListOfCities> generalListOfCities();

    //f
    @GET("regions")
    Call<GeneralListOfRegionsByCityId> generalListOfRegionsByCityId(@Query("city_id") int cityId);

    //f
    @POST("contact")
    Call<GeneralContactUs> generalContactUs(@Field("name") String name,
                                            @Field("email") String email,
                                            @Field("phone") String phone,
                                            @Field("type") String type,
                                            @Field("content") String content);


    @GET("payment-methods")
    Call<GeneralPaymentMethods> generalPaymentMethods();


    @GET("settings")
    Call<GeneralSettings> generalSettings(@Query("email") String email,
                                          @Query("password") int password);


    @POST("test-notification")
    Call<GeneralTestNotification> generalTestNotification(@Field("title") String title,
                                                          @Field("token") String token,
                                                          @Field("body") String body);

    //f
    @GET("offers")
    Call<GeneralListOfOffers> generalListOfOffers(@Query("restaurant_id") String restaurantId,
                                                  @Query("page") int page);


    @GET("offers")
    Call<GeneralOfferDetails> generalOfferDetails(@Query("offer_id") int offerId);

    //f
    @GET("categories")
    Call<GeneralListOfCategories> generalListOfCategories(@Query("restaurant_id") int restaurantId,
                                                          @Query("category_id") int categoryId);


    @GET("categories")
    Call<GeneralListOfCategories> generalListOfCategories(@Query("restaurant_id") int restaurantId);

    @GET("restaurant/my-categories")
    Call<GeneralListOfCategories> restaurantCategoriesList(@Query("api_token") String apiToken,
                                                           @Query("page") int page);

    //f
    @GET("restaurants")
    Call<GeneralListOfRestaurant> generalListOfRestaurant(@Query("page") int page);

    //f
    @GET("items")
    Call<GeneralListOfRestaurantItems> generalListOfRestaurantItems(@Query("restaurant_id") int restaurantId,
                                                                    @Query("category_id") int categoryId,
                                                                    @Query("page") int page);

    //f
    @GET("restaurants")
    Call<GeneralListOfRestaurantWithFilter> generalListOfRestaurantWithFilter(@Query("keyword") String keyword,
                                                                              @Query("region_id") int regionId,
                                                                              @Query("page") int page);


    @GET("restaurant")
    Call<GeneralRestaurantDetails> generalRestaurantDetails(@Query("restaurant_id") String restaurantId);

    //f
    @GET("reviews")
    Call<GeneralRestaurantReviews> generalRestaurantReviews(@Query("api_token") String apiToken,
                                                            @Query("restaurant_id") int restaurantId,
                                                            @Query("page") int page);

    //f
    @POST("change-password")
    @FormUrlEncoded
    Call<RestaurantChangePassword> restaurantChangePassword(@Field("api_token") String apiToken,
                                                            @Field("old_password") String oldPassword,
                                                            @Field("password") String password,
                                                            @Field("password_confirmation") String passwordConfirmation);

    //f
    @POST("login")
    @FormUrlEncoded
    Call<RestaurantLogin> restaurantLogin(@Field("email") String email,
                                          @Field("password") String password);

    //f
    @POST("new-password")
    @FormUrlEncoded
    Call<RestaurantNewPassword> restaurantNewPassword(@Field("code") String code,
                                                      @Field("password") String password,
                                                      @Field("password_confirmation") String passwordConfirmation);

    //f
    @POST("sign-up")
    @Multipart
    Call<RestaurantRegister> restaurantRegister(@Part("name") RequestBody name,
                                                @Part("email") RequestBody email,
                                                @Part("password") RequestBody password,
                                                @Part("password_confirmation") RequestBody passwordConfirmation,
                                                @Part("phone") RequestBody phone,
                                                @Part("whatsapp") RequestBody whatsapp,
                                                @Part("region_id") RequestBody regionId,
                                                @Part("delivery_cost") RequestBody delivery_cost,
                                                @Part("minimum_charger") RequestBody minimumCharger,
                                                @Part MultipartBody.Part profileImage,
                                                @Part("delivery_time") RequestBody deliveryTime);

    //f
    @POST("reset-password")
    @FormUrlEncoded
    Call<RestaurantResetPassword> restaurantResetPassword(@Field("email") String email);

    //f
    @POST("delete-category")
    @FormUrlEncoded
    Call<RestaurantDeleteCategory> restaurantDeleteCategory(@Field("api_token") String apiToken,
                                                            @Field("category_id") String categoryId);

    //f
    @POST("new-category")
    @Multipart
    Call<GeneralListOfCategories> restaurantNewCategory(@Part("name") RequestBody name,
                                                        @Part MultipartBody.Part photo,
                                                        @Part("api_token") RequestBody apiToken);

    //f
    @POST("update-category")
    @Multipart
    Call<RestaurantUpdateCategory> restaurantUpdateCategory(@Part("name") RequestBody name,
                                                            @Part MultipartBody.Part photo,
                                                            @Part("api_token") RequestBody apiToken,
                                                            @Part("category_id") RequestBody categoryId);

    //f
    @POST("delete-itemsData")
    @FormUrlEncoded
    Call<RestaurantDeleteFoodItem> restaurantDeleteFoodItem(@Field("item_id") int itemId,
                                                            @Field("api_token") String apiToken);

    //f
    @GET("my-items")
    Call<GeneralListOfRestaurantItems> restaurantMYFoodItems(@Query("api_token") String apiToken,
                                                             @Query("category_id") int categoryId,
                                                             @Query("page") int page);

    //f
    @POST("new-itemsData")
    @Multipart
    Call<RestaurantNewFoodItem> restaurantNewFoodItem(@Part("api_token") RequestBody apiToken,
                                                      @Part("category_id") RequestBody categoryId,
                                                      @Part("name") RequestBody name,
                                                      @Part("description") RequestBody description,
                                                      @Part("price") RequestBody price,
                                                      @Part("offer_price") RequestBody offerPrice,
                                                      @Part MultipartBody.Part photo);

    //f
    @POST("restaurant/update-itemsData")
    @Multipart
    Call<RestaurantNewFoodItem> restaurantUpdateItem(@Part("api_token") RequestBody apiToken,
                                                     @Part("item_id") RequestBody itemId,
                                                     @Part("name") RequestBody name,
                                                     @Part("description") RequestBody description,
                                                     @Part("price") RequestBody price,
                                                     @Part("offer_price") RequestBody offerPrice,
                                                     @Part MultipartBody.Part photo);

    //f
    @GET("notifications")
    Call<RestaurantListOfNotification> restaurantListOfNotification(@Query("api_token") String apiToken);


    @POST("signup-token")
    @FormUrlEncoded
    Call<RestaurantNotificationRegisterToken> restaurantNotificationRegisterToken(@Field("token") String token,
                                                                                  @Field("type") String type,
                                                                                  @Field("api_token") String apiToken);


    @POST("remove-token")
    @FormUrlEncoded
    Call<RestaurantNotificationRemoveToken> restaurantNotificationRemoveToken(@Field("token") String token,
                                                                              @Field("api_token") String apiToken);

    //f
    @POST("delete-offer")
    @FormUrlEncoded
    Call<GeneralOfferDetails> restaurantDeleteOffer(@Field("offer_id") String offerId,
                                                    @Field("api_token") String apiToken);

    //f
    @GET("my-offers")
    Call<GeneralOfferDetails> restaurantMyOffer(@Query("api_token") String apiToken,
                                              @Query("page") int page);

    //f
    @POST("new-offer")
    @Multipart
    Call<GeneralOfferDetails> restaurantNewOffer(@Part("name") RequestBody name,
                                                @Part("description") RequestBody description,
                                                @Part("price") RequestBody price,
                                                @Part("offer_price") RequestBody offer_price,
                                                @Part("starting_at") RequestBody starting_at,
                                                @Part("ending_at") RequestBody ending_at,
                                                @Part("api_token") RequestBody apiToken,
                                                @Part MultipartBody.Part photo);

    //f
    @POST("update-offer")
    @Multipart
    Call<RestaurantUpdateOffer> restaurantUpdateOffer(@Part("api_token") RequestBody apiToken,
                                                      @Part("offer_id") RequestBody offerId,
                                                      @Part("name") RequestBody name,
                                                      @Part("description") RequestBody description,
                                                      @Part("price") RequestBody price,
                                                      @Part("offer_price") RequestBody offer_price,
                                                      @Part("starting_at") RequestBody starting_at,
                                                      @Part("ending_at") RequestBody ending_at,
                                                      @Part MultipartBody.Part photo);

    //f
    @POST("accept-order")
    @FormUrlEncoded
    Call<RestaurantAcceptOrder> acceptOrder(@Field("api_token") String apiToken,
                                            @Field("order_id") String orderId);

    @POST("accept-order")
    @FormUrlEncoded
    Call<RestaurantAcceptOrder> cancelOrder(@Field("api_token") String apiToken,
                                            @Field("order_id") String orderId);

    //f
    @POST("confirm-order")
    @FormUrlEncoded
    Call<RestaurantAcceptOrder> confirmOrder(@Field("api_token") String apiToken,
                                             @Field("order_id") String orderId);


    @POST("commissions")
    @FormUrlEncoded
    Call<RestaurantCommission> restaurantCommission(@Field("api_token") String apiToken);


    @GET("commissions")
    Call<RestaurantGetCommission> restaurantGetCommission(@Query("api_token") String apiToken);

    //f
    @GET("my-orders")
    Call<RestaurantMyOrders> restaurantMyOrders(@Query("api_token") String apiToken,
                                                @Query("state") String state,
                                                @Query("page") int page);


    //f
    @POST("reject-order")
    @FormUrlEncoded
    Call<RestaurantRejectOrder> rejectOrder(@Field("api_token") String apiToken,
                                            @Field("order_id") int orderId,
                                            @Field("refuse_reason") int refuseReason);


    @GET("my-orders")
    Call<RestaurantShowOrder> restaurantShowOrder(@Query("api_token") String apiToken,
                                                  @Query("order_id") String orderId);

    //f
    @POST("profile")
    @Multipart
    Call<RestaurantRegister> EditRestaurantProfile(@Part("api_token") RequestBody apiToken,
                                                   @Part("name") RequestBody name,
                                                   @Part("email") RequestBody email,
                                                   @Part("phone") RequestBody phone,
                                                   @Part("whatsapp") RequestBody whatsapp,
                                                   @Part("region_id") RequestBody regionId,
                                                   @Part("delivery_cost") RequestBody delivery_cost,
                                                   @Part("minimum_charger") RequestBody minimumCharger,
                                                   @Part("availability") RequestBody availability,
                                                   @Part MultipartBody.Part profileImage,
                                                   @Part("delivery_time") RequestBody deliveryTime);


    @POST("change-state")
    @FormUrlEncoded
    Call<RestaurantChangeState> restaurantChangeState(@Field("state") String state,
                                                      @Field("api_token") int apiToken);


    @POST("profile")
    @FormUrlEncoded
    Call<RestaurantGetUserProfile> restaurantGetUserProfile(@Field("api_token") int apiToken);
}

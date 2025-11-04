Vitamin-Deficiency-Detection-using-Image-Processing-and-Machine-Learning. 


The idea is to build a non-invasive and cost-effective tool to detect vitamin deficiencies from visible symptoms in body parts like nails and eyes. Created an Android app where users can upload or capture an image. First, the image undergoes processing to clean and standardize it. Then, we extract key features such as texture and color. These features are passed to a CNN model, which was trained on a dataset of labeled images. The model predicts the likely vitamin deficiency, and the result is displayed back to the user in the app.

For implementation, we used Python, TensorFlow/Keras for model training, and connected it to the Android frontend through APIs. Our model achieved good accuracy, and we demonstrated the app working with live images.

The significance is that it can provide early detection and awareness, though it’s not a replacement for lab tests. In the future, we can expand this to detect mineral deficiencies and provide personalized health recommendations.

Workflow:

Image Capture – User uploads/captures image (nails/eyes).

Image Processing – We preprocess the image (resize, noise removal, enhance quality).

Feature Extraction – Extract color, texture, shape features that may indicate deficiencies.

Machine Learning (CNN model) – Trained on dataset of images with known deficiencies; it learns to classify based on patterns.

Prediction – The backend model predicts the deficiency type (e.g., Vitamin D, B12).

Frontend – The result is shown in the app with user-friendly info.

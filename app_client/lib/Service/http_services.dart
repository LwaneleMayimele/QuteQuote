import 'dart:convert';
import 'dart:io';
import 'package:app_client/DTO/quote.dart';
import 'package:http/http.dart' as http;

import '../main.dart';

class HttpServices {
  Future<http.Response> getAllQuotes() async {
    // HttpOverrides.global = MyHttpOverrides();
    var client = http.Client();
    try {
      // print('Starting >>>>');
      final response = await client
          .get(Uri.parse('http://f00a-102-132-201-218.ngrok.io/quotes/'));
      if (response.statusCode == 200) {
        return response;
      } else {
        throw Exception('Failed to load album');
      }
    } finally {
      client.close();
    }
  }

  Future<http.Response> postQuote(String authorName, String quote) async {
    final response = await http.post(
        Uri.parse('http://20.20.17.26:5000/quotes'),
        body: jsonEncode(
            <String, String>{"authorName": authorName, "quote": quote}));
    if (response.statusCode == 200) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      return response;
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to save');
    }
  }

  Future<QuoteDTO> deleteQoute(String authorName, String quote) async {
    final response =
        await http.delete(Uri.parse('http://20.20.17.26:7000/quotes'));

    if (response.statusCode == 201) {
      // If the server did return a 200 OK response,
      // then parse the JSON.
      return QuoteDTO.fromJson(jsonDecode(response.body));
    } else {
      // If the server did not return a 200 OK response,
      // then throw an exception.
      throw Exception('Failed to load album');
    }
  }
}



// // void main() async {
// //   HttpOverrides.global = MyHttpOverrides();
// //   HttpServices hg = HttpServices();
// //   Future<http.Response> q = hg.fetchAllQuotes();

// //   q.then((value) => print("The name is ${value.body}"));
// // }

// // class MyHttpOverrides extends HttpOverrides {
// //   @override
// //   HttpClient createHttpClient(SecurityContext? context) {
// //     return super.createHttpClient(context)
// //       ..badCertificateCallback =
// //           (X509Certificate cert, String host, int port) => true;
// //   }
// // }

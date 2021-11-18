import 'dart:convert';
import 'dart:ui';
import 'package:app_client/Model/all_quote.dart';
import 'package:app_client/Model/quote_model.dart';
import 'package:app_client/main.dart';

String json =
    '{"quotes": [{"quote":"Life isn’t about getting and having, it’s about giving and being.","author":"Kevin Kruse"},{"quote":"Whatever the mind of man can conceive and believe, it can achieve.","author":"Napoleon Hill"},{"quote":"Strive not to be a success, but rather to be of value.","author":"Albert Einstein"}]}';

var j = jsonDecode(json).cast<Map<String, dynamic>>();

void main(List<String> args) {
  print(j[0]);
  // print(j.map<Quote>((sk) => Quote.fromJson(sk)).toList());
}

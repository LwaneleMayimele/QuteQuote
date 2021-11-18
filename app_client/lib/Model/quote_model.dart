import 'package:app_client/DTO/quote.dart';
import 'package:app_client/Domain/app_domain.dart';
import 'package:flutter/cupertino.dart';

class QuoteModel extends ChangeNotifier {
  final AppDomain _domain = AppDomain();

  Future<bool> postQuote(String author, String quote) async {
    return await _domain.postQuote(author, quote);
  }

  Future<List<QuoteDTO>> getAllQuote() async {
    return await _domain.getAllQuote();
  }
}

import 'package:app_client/Controllars/qoute_controllar.dart';
import 'package:app_client/View/view_quotes.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

class HomePage extends StatelessWidget {
  const HomePage({Key? key}) : super(key: key);
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
      appBar: AppBar(
        title: const Text('Qute-Qoute'),
        centerTitle: true,
        backgroundColor: Colors.teal,
      ),
      backgroundColor: Colors.teal,
      body: SafeArea(
          child: Center(
              child: Column(
        children: [
          Column(
            children: [
              const SizedBox(height: 20.0),
              const CircleAvatar(
                radius: 50.0,
                backgroundImage: AssetImage('Images/Lwanele.png'),
              ),
              const Text(
                'The Quotes',
                style: TextStyle(
                    fontSize: 30.0,
                    fontFamily: 'Pacifico',
                    color: Colors.white,
                    fontWeight: FontWeight.bold),
              ),
              Text(
                'Where great minds meet',
                style: TextStyle(
                    fontSize: 20.0,
                    fontFamily: 'Source Sans Pro',
                    fontWeight: FontWeight.bold,
                    color: Colors.teal.shade100,
                    letterSpacing: 2.5),
              ),
              const SizedBox(height: 20.0),
              Container(
                padding: const EdgeInsets.all(10.0),
                margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 5),
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: Colors.white,
                  ),
                  onPressed: () async {
                    var quoteList = await Provider.of<QuoteControllar>(context,
                            listen: false)
                        .quoteList;
                    Navigator.push(
                        context,
                        MaterialPageRoute(
                            builder: (context) => ViewQuotes(quoteList)));
                  },
                  child: Row(
                    children: [
                      Icon(
                        Icons.auto_stories,
                        color: Colors.teal.shade900,
                      ),
                      const SizedBox(
                        width: 75.0,
                      ),
                      Text(
                        'VIEW QUOTES',
                        style: TextStyle(
                          color: Colors.teal.shade900,
                          fontFamily: "Source Sans Pro",
                          fontSize: 20.0,
                        ),
                      )
                    ],
                  ),
                ),
              ),
              const SizedBox(height: 5.0),
              Container(
                padding: const EdgeInsets.all(10.0),
                margin: const EdgeInsets.symmetric(vertical: 10, horizontal: 5),
                child: TextButton(
                  style: TextButton.styleFrom(
                    backgroundColor: Colors.white,
                  ),
                  onPressed: () async {
                    Provider.of<QuoteControllar>(context).btnViewQuote(context);
                  },
                  child: Row(
                    children: [
                      Icon(
                        Icons.add,
                        color: Colors.teal.shade900,
                      ),
                      const SizedBox(
                        width: 80.0,
                      ),
                      Text(
                        'ADD QUOTE',
                        style: TextStyle(
                          color: Colors.teal.shade900,
                          fontFamily: "Source Sans Pro",
                          fontSize: 20.0,
                        ),
                      )
                    ],
                  ),
                ),
              ),
            ],
          ),
        ],
      ))),
    ));
  }
}

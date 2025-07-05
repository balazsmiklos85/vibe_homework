#!/usr/bin/python3

import os
import json
import urllib.request
import readline
from datetime import datetime


def main():
    """
    A simple Python script to chat with Google Gemini using only standard libraries.
    """
    api_key = os.environ.get("GEMINI_API_KEY")
    if not api_key:
        print("Error: GEMINI_API_KEY environment variable not set.")
        return
    try:
        with open("system_prompt.md", "r") as f:
            system_prompt = f.read().strip()
    except FileNotFoundError:
        print("Error: system_prompt.md not found.")
        return
    # The REST API endpoint for the model
    url = f"https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent?key={api_key}"

    # Conversation history
    history = []

    print("# Chat with Gemini.\n")
    print("Type '/quit' to exit.\n")
    print("## System prompt:\n")
    print(f"\"\"\"\n{system_prompt}\n\"\"\"\n")
    print("## Conversation history:\n")
    while True:
        try:
            user_input = input("> [User:] ")
            if user_input.lower() == "/quit":
                break
            print("")
            print(f"`<<< Sent {datetime.now().isoformat()} <<<`\n")

            # Add user input to history
            history.append({"role": "user", "parts": [{"text": user_input}]})

            # Prepare the request payload
            payload = {
                "contents": history,
                "system_instruction": {
                    "role": "user",
                    "parts": [{"text": system_prompt}],
                },
                "generationConfig": {
                    "temperature": 0.0,
                    "topK": 1,
                    "topP": 1,
                    "maxOutputTokens": 2048,
                },
            }

            # Create and send the request
            req = urllib.request.Request(
                url,
                data=json.dumps(payload).encode("utf-8"),
                headers={"Content-Type": "application/json"},
                method="POST",
            )

            with urllib.request.urlopen(req) as response:
                if response.status == 200:
                    response_data = json.loads(response.read().decode("utf-8"))
                    # Extract the model's response text
                    model_response = response_data["candidates"][0]["content"]["parts"][
                        0
                    ]["text"]
                    print(f"`>>> Received {datetime.now().isoformat()} >>>`")
                    print(f"\n> [Model:] {model_response}\n") 
                    # Add model response to history
                    history.append(
                        {"role": "model", "parts": [{"text": model_response}]}
                    )
                else:
                    print(
                        f"```\nError: API request failed with status {response.status}\n```")
                    print(response.read().decode("utf-8"))
                    # Remove the last user message from history on failure
                    history.pop()

        except EOFError:
            break
        except Exception as e:
            print(f"```\nAn error occurred: {e}\n```")
            # Clean up history if something went wrong
            if history and history[-1]["role"] == "user":
                history.pop()
            break


if __name__ == "__main__":
    main()

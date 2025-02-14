#include <iostream>
using namespace std;

class Log
{
    public:
        enum Level 
        {
            ERROR, WARN, INFO
        };
    private:
        Level m_logLevel = INFO;
    public:
        void setLevel(Level level)
        {
            m_logLevel = level;
        }

        void info(const char* message)
        {
            if (m_logLevel >= INFO) {
                cout << "[INFO] " << message << endl;
            }
        }

        void warn(const char* message)
        {
            if (m_logLevel >= WARN) {
                cout << "[WARN] " << message << endl;
            }
        }

        void error(const char* message)
        {
            if (m_logLevel >= ERROR) {
                cout << "[ERROR] " << message << endl;
            }
        }
};

int main()
{
    Log log;
    log.setLevel(Log::INFO);
    log.info("Hello!");
    log.warn("some warning message");
    log.error("some error message");

    cin.get();
}
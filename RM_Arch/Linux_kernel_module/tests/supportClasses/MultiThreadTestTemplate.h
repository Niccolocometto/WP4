#ifndef KERNEL_MULTITHREADTESTTEMPLATE_H
#define KERNEL_MULTITHREADTESTTEMPLATE_H

#include <cppunit/TestFixture.h>
#include <cppunit/extensions/HelperMacros.h>

#include "MultiThreadTestReaderStats.h"
#include "MultiThreadTestStats.h"
#include "../../lib/cpp/Event.h"
#include "StressData.h"

#define BUFFER_SIZE 64
#define STRESS_DATA_SIZE 5
#define NUMBER_OF_EVENTS_D 999999
#define NUMBER_OF_READERS_D 1
#define SCHEDULE_POLICY SCHED_FIFO
#define PRIORITY 99
#define WRITER_PERIOD_SEC 0
#define WRITER_PERIOD_NSEC 30000
#define READER_PERIOD_SEC 0
#define READER_PERIOD_NSEC 30000

/**
 * A template class for multi thread testing.
 *
 * MultiThread test lets a writer and multiple readers race against each other, the readers check that the integrity  data popped from the buffers is correct. By nature this test is necessarily stochastic, and the test tries to give 99% confidence that the EventBuffers are error free.
 *
 * The users should calibrate the test parameters such as the buffer size, the stress data size, the number of events, number of readers, the readers schedule policy, priority and
 *
 * This class facilitates multi thread testing by performing required operations such as spawning reader/writer processes,
 * printing the test configuration, printing the test results, scheduling the reader/writers, updating statistics, etc.
 *
 * Child classes implement the read/write functions.
 *
 * @author Humberto Carvalho (1120409@isep.ipp.pt)
 * @date
 */
class MultiThreadTestTemplate {
private:
    /**
     * The pointer to the reader shared_states
     */
    MultiThreadTestReaderStats * shared_stats;

    /**
     * The statistics for the writer.
     */
    MultiThreadTestStats writer_stats;

    /**
     * The pids of the readers.
     */
    pid_t readers[NUMBER_OF_READERS_D];

    /**
     * How many readers currently exist.
     */
    size_t active_readers;

    /**
    * Prints the test configuration.
    */
    void print_test_config();

    /**
     * Print test stats to stdin.
     *
     * The stats produced are:
     * - Read elements.
     * - Number of errors.
     * - Number of gaps.
     * - Number of false gaps.
     * - Number of empty reads.
     * - Min read time.
     * - Max read time.
     * - Max gap read time.
     * - Min gap read time.
     * - Max gap read time.
     * - Max read loop time.
     * - Min read loop time.
     * - Max write time.
     * - Min write time.
     *
     * @param writer_stats the stats produced by the writers.
     * @param reader_stats the stats produced by the writers.
     */
    void print_test_stats(const MultiThreadTestStats & writer_stats, const MultiThreadTestReaderStats & readerStats, const __time_t time);

    /**
    * The write function.
    *
    * This is the write function the writer thread will call and execute.
    */
    virtual void writer_function(MultiThreadTestStats & stats) = 0;

    /**
    * The read function.
    *
    * This is the read function the reader thread will call and execute.
    */
    virtual void reader_function(MultiThreadTestReaderStats * stats) = 0;

    /**
     * Initializes the necessary resources for the test.
     */
    virtual void init() = 0;

    /**
     * Deallocates the resources used for the test.
     */
    virtual void term() = 0;

    /**
     * Gets the test name so it can be printed on the terminal.
     *
     * @return the test name.
     */
    virtual std::string get_test_name() const=0;

protected:
    /**
    * Creates a new MultiThreadTestTemplate.
    */
    MultiThreadTestTemplate();

    /**
     * Destroys a MultiThreadTestTemplate.
     */
    ~MultiThreadTestTemplate();

    /**
    * The reader period.
    */
    struct timespec reader_period;

    /**
    * The writer period.
    */
    struct timespec writer_period;

    /**
    *  The number of events which will be written to the buffers.
    */
    const unsigned long long NUMBER_OF_EVENTS;

    /**
    * The number of readers.
    */
    const unsigned short NUMBER_OF_READERS;

    /**
    * The schedule policy for both readers/writers.
    */
    const int schedule_policy;

    /**
     * The schedule priority for both readers/writers.
     */
    const int priority;

    /**
     *  Schedule the writer and update the writer loop stats.
     *
     * The updated stats are:
     * - The min/max write time.
     * - the min/max loop write time.
     * - Number of deadline misses.
     *
     *  @param stats the stats variable to update.
     *  @param next the next deadline.
     *  @param last loop start time.
     */
    void writer_schedule(MultiThreadTestStats * stats, struct timespec & next, struct timespec & last);

    /**
     *  Schedule the reader and update the reader loop stats.
     *
     * The updated stats are:
     * - The min/max read time.
     * - the min/max loop read time.
     * - Number of deadline misses.
     *
     *  @param stats the stats variable to update.
     *  @param next the next deadline.
     *  @param last loop start time.
     */
    void reader_schedule(MultiThreadTestReaderStats * stats, struct timespec & next, struct timespec & last);

    /**
     * Performs checks that verify the data is sequentially  correct, and updates the reader stats.
     *
     * The data integrity checks are:
     * - If a gap occurs the current timestamp and the popped data must be > than the last read.
     * - The current timestamp and the data cannot ever be < than the last read.
     * - Checks data integrity.
     * - Checks for false gaps.
     *
     * The stats updates are:
     * - Number of gaps.
     * - Number of false gaps.
     * - Number of reads.
     * - Number of empty reads.
     * - Number of errors.
     *
     * @param stats the stats variable to update.
     * @param counter the data integrity counter.
     * @param event the last read event.
     * @param last_read_timestamp the last read timestamp.
     * @param pop if an element was popped.
     * @param gap if a gap occured.
     * @param start the pop_begin time.
     * @param pop_end the pop_end time.
     */
    void reader_read_checks(MultiThreadTestReaderStats * stats, unsigned long long & counter, Event<StressData<STRESS_DATA_SIZE> > & event, struct timespec & last_read_timestamp, bool & pop, bool & gap,  struct timespec & start, struct timespec & pop_end);

public:
    /**
     * Runs the test, printing the results to stdin.
     *
     * Creates reader/writer processes setting and executes the reader/writer functions. The processes run with the specified priority and periodicity.
     *
     * If for some reason we fail to set the thread priority we will print an error message but the test will still continue.
     */
    void run();
};


#endif //KERNEL_MULTITHREADTESTTEMPLATE_H
